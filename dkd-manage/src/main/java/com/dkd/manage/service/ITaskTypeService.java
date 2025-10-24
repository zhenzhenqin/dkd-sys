package com.dkd.manage.service;

import java.util.List;
import com.dkd.manage.domain.TaskType;

/**
 * 工单类型Service接口
 * 
 * @author itheima
 * @date 2025-10-24
 */
public interface ITaskTypeService 
{
    /**
     * 查询工单类型
     * 
     * @param typeId 工单类型主键
     * @return 工单类型
     */
    public TaskType selectTaskTypeByTypeId(Long typeId);

    /**
     * 查询工单类型列表
     * 
     * @param taskType 工单类型
     * @return 工单类型集合
     */
    public List<TaskType> selectTaskTypeList(TaskType taskType);

    /**
     * 新增工单类型
     * 
     * @param taskType 工单类型
     * @return 结果
     */
    public int insertTaskType(TaskType taskType);

    /**
     * 修改工单类型
     * 
     * @param taskType 工单类型
     * @return 结果
     */
    public int updateTaskType(TaskType taskType);

    /**
     * 批量删除工单类型
     * 
     * @param typeIds 需要删除的工单类型主键集合
     * @return 结果
     */
    public int deleteTaskTypeByTypeIds(Long[] typeIds);

    /**
     * 删除工单类型信息
     * 
     * @param typeId 工单类型主键
     * @return 结果
     */
    public int deleteTaskTypeByTypeId(Long typeId);
}
