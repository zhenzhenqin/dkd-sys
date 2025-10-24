package com.dkd.manage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.TaskTypeMapper;
import com.dkd.manage.domain.TaskType;
import com.dkd.manage.service.ITaskTypeService;

/**
 * 工单类型Service业务层处理
 * 
 * @author itheima
 * @date 2025-10-24
 */
@Service
public class TaskTypeServiceImpl implements ITaskTypeService 
{
    @Autowired
    private TaskTypeMapper taskTypeMapper;

    /**
     * 查询工单类型
     * 
     * @param typeId 工单类型主键
     * @return 工单类型
     */
    @Override
    public TaskType selectTaskTypeByTypeId(Long typeId)
    {
        return taskTypeMapper.selectTaskTypeByTypeId(typeId);
    }

    /**
     * 查询工单类型列表
     * 
     * @param taskType 工单类型
     * @return 工单类型
     */
    @Override
    public List<TaskType> selectTaskTypeList(TaskType taskType)
    {
        return taskTypeMapper.selectTaskTypeList(taskType);
    }

    /**
     * 新增工单类型
     * 
     * @param taskType 工单类型
     * @return 结果
     */
    @Override
    public int insertTaskType(TaskType taskType)
    {
        return taskTypeMapper.insertTaskType(taskType);
    }

    /**
     * 修改工单类型
     * 
     * @param taskType 工单类型
     * @return 结果
     */
    @Override
    public int updateTaskType(TaskType taskType)
    {
        return taskTypeMapper.updateTaskType(taskType);
    }

    /**
     * 批量删除工单类型
     * 
     * @param typeIds 需要删除的工单类型主键
     * @return 结果
     */
    @Override
    public int deleteTaskTypeByTypeIds(Long[] typeIds)
    {
        return taskTypeMapper.deleteTaskTypeByTypeIds(typeIds);
    }

    /**
     * 删除工单类型信息
     * 
     * @param typeId 工单类型主键
     * @return 结果
     */
    @Override
    public int deleteTaskTypeByTypeId(Long typeId)
    {
        return taskTypeMapper.deleteTaskTypeByTypeId(typeId);
    }
}
