package com.dkd.manage.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.dkd.common.constant.DkdContants;
import com.dkd.common.exception.ServiceException;
import com.dkd.common.utils.DateUtils;
import com.dkd.manage.domain.Emp;
import com.dkd.manage.domain.TaskDetails;
import com.dkd.manage.domain.VendingMachine;
import com.dkd.manage.domain.dto.TaskDetailsDto;
import com.dkd.manage.domain.dto.TaskDto;
import com.dkd.manage.domain.vo.TaskVo;
import com.dkd.manage.mapper.EmpMapper;
import com.dkd.manage.mapper.TaskDetailsMapper;
import com.dkd.manage.mapper.VendingMachineMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.TaskMapper;
import com.dkd.manage.domain.Task;
import com.dkd.manage.service.ITaskService;

/**
 * 工单Service业务层处理
 * 
 * @author itheima
 * @date 2025-10-24
 */
@Service
public class TaskServiceImpl implements ITaskService 
{
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private VendingMachineMapper vendingMachineMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TaskDetailsMapper taskDetailsMapper;

    /**
     * 查询工单
     * 
     * @param taskId 工单主键
     * @return 工单
     */
    @Override
    public Task selectTaskByTaskId(Long taskId)
    {
        return taskMapper.selectTaskByTaskId(taskId);
    }

    /**
     * 查询工单列表
     * 
     * @param task 工单
     * @return 工单
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增工单
     * 
     * @param task 工单
     * @return 结果
     */
    @Override
    public int insertTask(Task task)
    {
        task.setCreateTime(DateUtils.getNowDate());
        return taskMapper.insertTask(task);
    }

    /**
     * 修改工单
     * 
     * @param task 工单
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除工单
     * 
     * @param taskIds 需要删除的工单主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskIds(Long[] taskIds)
    {
        return taskMapper.deleteTaskByTaskIds(taskIds);
    }

    /**
     * 删除工单信息
     * 
     * @param taskId 工单主键
     * @return 结果
     */
    @Override
    public int deleteTaskByTaskId(Long taskId)
    {
        return taskMapper.deleteTaskByTaskId(taskId);
    }

    /**
     * 查询工单列表
     *
     * @param task 工单
     * @return taskVo集合
     */
    @Override
    public List<TaskVo> selectTaskVoList(Task task) {
        return taskMapper.selectTaskVoList(task);
    }

    /**
     * 新增工单
     *
     * @param taskDto 工单
     * @return 结果
     */
    @Override
    public int insertTaskDto(TaskDto taskDto) {
        //1.通过售货机编码查询是否存在此售货机
        VendingMachine vendingMachine = vendingMachineMapper.getVendingMachineByInnerCode(taskDto.getInnerCode());
        if(vendingMachine == null){
            throw new ServiceException("设备不存在");
        }

        //2.检验售货机的状态是否与工单的类型一致
        //vmStatus : 售货机状态  name = "设备状态，0:未投放;1-运营;3-撤机"
        // productTypeId : 工单类型  1 - 投放工单   2 - 补货工单   3 - 维修工单   4 - 撤机工单
        checkVendingMachineStatus(vendingMachine.getVmStatus(), taskDto.getProductTypeId());

        //3.检查设备是否有未完成的同类型工单
        hasTask(taskDto);

        //4.查询员工是否存在
        Emp emp = empMapper.selectEmpById(taskDto.getUserId());
        if(emp == null){
            throw new ServiceException("员工不存在");
        }

        //5.判断员工是否在可操作的区域内
        if(!emp.getRegionId().equals(vendingMachine.getRegionId())){
            throw new ServiceException("员工不在该售货机的区域");
        }

        //6. 将dto对象转化为po对象
        Task task = new Task();
        BeanUtils.copyProperties(taskDto, task);
        task.setTaskStatus(DkdContants.TASK_STATUS_CREATE);
        task.setUserName(emp.getUserName());
        task.setRegionId(emp.getRegionId());
        task.setAddr(vendingMachine.getAddr());
        task.setTaskCode(generateTaskCode());  //设置工单编号
        // 将po对象插入数据库
        int result = taskMapper.insertTask(task);

        //7.判断是否为补货工单 补货工单需要插入工单的详细数据
        if(taskDto.getProductTypeId() == DkdContants.TASK_TYPE_SUPPLY){
            //8.  保存工单详细数据
            List<TaskDetailsDto> details = taskDto.getDetails();
            if(details == null || details.size() == 0){
                throw new ServiceException("补货工单详情不能为空");
            }

            //将dto转化为po对象
            List<TaskDetails> taskDetailsList = new ArrayList<>();
            for(TaskDetailsDto detailsDto : details){
                TaskDetails taskDetails = new TaskDetails();
                BeanUtils.copyProperties(detailsDto, taskDetails);
                taskDetails.setTaskId(task.getTaskId());
                //将工单详细数据加入集合
                taskDetailsList.add(taskDetails);
            }

            //将工单详细数据集合批量插入数据库中
            taskDetailsMapper.batchInsertTaskDetail(taskDetailsList);
        }

        return result;

    }

    /**
     * 取消工单
     * @param task 工单
     * @return 结果
     */
    @Override
    public int cancelTask(Task task) {
        //1. 检验工单是否存在
        Task ts = taskMapper.selectTaskByTaskId(task.getTaskId());
        if(ts == null){
            throw new ServiceException("工单不存在");
        }

        //2. 检验工单状态是否已经被取消
        if(ts.getTaskStatus() == DkdContants.TASK_STATUS_CANCEL){
            throw new ServiceException("工单已经取消");
        }

        //3. 检验工单状态是否已经完成
        if(ts.getTaskStatus() == DkdContants.TASK_STATUS_FINISH){
            throw new ServiceException("工单已经完成");
        }


        //4. 设置相关信息字段
        task.setTaskStatus(DkdContants.TASK_STATUS_CANCEL);
        task.setUpdateTime(DateUtils.getNowDate());

        return taskMapper.updateTask(task);
    }

    /**
     * 生成并获取当天任务代码的唯一标识。
     * 该方法首先尝试从Redis中获取当天的任务代码计数，如果不存在，则初始化为1并返回"日期0001"格式的字符串。
     * 如果存在，则对计数加1并返回更新后的任务代码。
     *
     * @return 返回当天任务代码的唯一标识，格式为"日期XXXX"，其中XXXX是四位数字的计数。
     */
    public String generateTaskCode() {
        // 获取当前日期并格式化为"yyyyMMdd"
        String dateStr = DateUtils.getDate().replaceAll("-", "");
        // 根据日期生成redis的键
        String key = "dkd.task.code." + dateStr;
        // 判断key是否存在
        if (!redisTemplate.hasKey(key)) {
            // 如果key不存在，设置初始值为1，并指定过期时间为1天
            redisTemplate.opsForValue().set(key, 1, Duration.ofDays(1));
            // 返回工单编号（日期+0001）
            return dateStr + "0001";
        }
        // 如果key存在，计数器+1（0002），确保字符串长度为4位
        return dateStr+ StrUtil.padPre(redisTemplate.opsForValue().increment(key).toString(),4,'0');
    }

    /**
     * 用于检验设备是否已经有未完成的同类型订单
     * @param taskDto
     */
    private void hasTask(TaskDto taskDto) {
        Task task = new Task();
        task.setInnerCode(taskDto.getInnerCode());
        task.setProductTypeId(taskDto.getProductTypeId());
        task.setTaskStatus(DkdContants.TASK_STATUS_PROGRESS);

        List<Task> taskList = taskMapper.selectTaskList(task);
        if(taskList != null && taskList.size() > 0){
            throw new ServiceException("该设备已经有未完成的工单");
        }
    }

    /**
     * 检验售货机的状态是否与工单的类型一致
     * @param vmStatus 售货机状态
     * @param productTypeId 工单类型
     *
     * vmStatus : 售货机状态  name = "设备状态，0:未投放;1-运营;3-撤机"
     * productTypeId : 工单类型  1 - 投放工单   2 - 补货工单   3 - 维修工单   4 - 撤机工单
     */
    private void checkVendingMachineStatus(Long vmStatus, Long productTypeId) {
        //如果是投放工单 售货机的状态不能是已投放 否则抛出异常
        if (productTypeId == DkdContants.TASK_TYPE_DEPLOY && vmStatus == DkdContants.VM_STATUS_RUNNING){
            throw new ServiceException("该设备正在运行中，不能进行投放");
        }

        //如果是补货工单，售货机的状态如果不是运行中，则抛出异常
        if(productTypeId == DkdContants.TASK_TYPE_SUPPLY && vmStatus != DkdContants.VM_STATUS_RUNNING){
            throw new ServiceException("该设备不在运行中，不能进行补货");
        }

        //如果是维修工单，售货机的状态不是运行中 将会抛出异常 因为不在运行的售货机不需要维修
        if(productTypeId == DkdContants.TASK_TYPE_REPAIR && vmStatus != DkdContants.VM_STATUS_RUNNING){
            throw new ServiceException("该设备不在运行中，不能进行维修");
        }

        //如果是撤机工单，则售货机的状态不是运营中 则抛出异常
        if(productTypeId == DkdContants.TASK_TYPE_REVOKE && vmStatus != DkdContants.VM_STATUS_RUNNING){
            throw new ServiceException("该设备不在运行中，不能进行撤机");
        }
    }
}
