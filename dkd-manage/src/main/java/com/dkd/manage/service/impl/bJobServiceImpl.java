package com.dkd.manage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.bJobMapper;
import com.dkd.manage.domain.bJob;
import com.dkd.manage.service.IbJobService;

/**
 * 自动补货任务Service业务层处理
 * 
 * @author itheima
 * @date 2025-10-24
 */
@Service
public class bJobServiceImpl implements IbJobService 
{
    @Autowired
    private bJobMapper bJobMapper;

    /**
     * 查询自动补货任务
     * 
     * @param id 自动补货任务主键
     * @return 自动补货任务
     */
    @Override
    public bJob selectbJobById(Long id)
    {
        return bJobMapper.selectbJobById(id);
    }

    /**
     * 查询自动补货任务列表
     * 
     * @param bJob 自动补货任务
     * @return 自动补货任务
     */
    @Override
    public List<bJob> selectbJobList(bJob bJob)
    {
        return bJobMapper.selectbJobList(bJob);
    }

    /**
     * 新增自动补货任务
     * 
     * @param bJob 自动补货任务
     * @return 结果
     */
    @Override
    public int insertbJob(bJob bJob)
    {
        return bJobMapper.insertbJob(bJob);
    }

    /**
     * 修改自动补货任务
     * 
     * @param bJob 自动补货任务
     * @return 结果
     */
    @Override
    public int updatebJob(bJob bJob)
    {
        return bJobMapper.updatebJob(bJob);
    }

    /**
     * 批量删除自动补货任务
     * 
     * @param ids 需要删除的自动补货任务主键
     * @return 结果
     */
    @Override
    public int deletebJobByIds(Long[] ids)
    {
        return bJobMapper.deletebJobByIds(ids);
    }

    /**
     * 删除自动补货任务信息
     * 
     * @param id 自动补货任务主键
     * @return 结果
     */
    @Override
    public int deletebJobById(Long id)
    {
        return bJobMapper.deletebJobById(id);
    }
}
