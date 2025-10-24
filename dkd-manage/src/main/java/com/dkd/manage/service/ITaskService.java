package com.dkd.manage.service;

import java.util.List;
import com.dkd.manage.domain.Task;
import com.dkd.manage.domain.vo.TaskVo;

/**
 * 工单Service接口
 * 
 * @author itheima
 * @date 2025-10-24
 */
public interface ITaskService 
{
    /**
     * 查询工单
     * 
     * @param taskId 工单主键
     * @return 工单
     */
    public Task selectTaskByTaskId(Long taskId);

    /**
     * 查询工单列表
     * 
     * @param task 工单
     * @return 工单集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增工单
     * 
     * @param task 工单
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改工单
     * 
     * @param task 工单
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 批量删除工单
     * 
     * @param taskIds 需要删除的工单主键集合
     * @return 结果
     */
    public int deleteTaskByTaskIds(Long[] taskIds);

    /**
     * 删除工单信息
     * 
     * @param taskId 工单主键
     * @return 结果
     */
    public int deleteTaskByTaskId(Long taskId);

    /**
     * 查询工单列表
     *
     * @param task 工单
     * @return taskVo集合
     */
    public List<TaskVo> selectTaskVoList(Task task);
}
