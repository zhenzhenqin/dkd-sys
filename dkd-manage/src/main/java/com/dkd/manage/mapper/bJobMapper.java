package com.dkd.manage.mapper;

import java.util.List;
import com.dkd.manage.domain.bJob;

/**
 * 自动补货任务Mapper接口
 * 
 * @author itheima
 * @date 2025-10-24
 */
public interface bJobMapper 
{
    /**
     * 查询自动补货任务
     * 
     * @param id 自动补货任务主键
     * @return 自动补货任务
     */
    public bJob selectbJobById(Long id);

    /**
     * 查询自动补货任务列表
     * 
     * @param bJob 自动补货任务
     * @return 自动补货任务集合
     */
    public List<bJob> selectbJobList(bJob bJob);

    /**
     * 新增自动补货任务
     * 
     * @param bJob 自动补货任务
     * @return 结果
     */
    public int insertbJob(bJob bJob);

    /**
     * 修改自动补货任务
     * 
     * @param bJob 自动补货任务
     * @return 结果
     */
    public int updatebJob(bJob bJob);

    /**
     * 删除自动补货任务
     * 
     * @param id 自动补货任务主键
     * @return 结果
     */
    public int deletebJobById(Long id);

    /**
     * 批量删除自动补货任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletebJobByIds(Long[] ids);
}
