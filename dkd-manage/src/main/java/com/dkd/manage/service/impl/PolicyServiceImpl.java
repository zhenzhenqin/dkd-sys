package com.dkd.manage.service.impl;

import java.util.List;
import com.dkd.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.PolicyMapper;
import com.dkd.manage.domain.Policy;
import com.dkd.manage.service.IPolicyService;

/**
 * 策略管理Service业务层处理
 * 
 * @author itheima
 * @date 2025-10-22
 */
@Service
public class PolicyServiceImpl implements IPolicyService 
{
    @Autowired
    private PolicyMapper policyMapper;

    /**
     * 查询策略管理
     * 
     * @param policyId 策略管理主键
     * @return 策略管理
     */
    @Override
    public Policy selectPolicyByPolicyId(Long policyId)
    {
        return policyMapper.selectPolicyByPolicyId(policyId);
    }

    /**
     * 查询策略管理列表
     * 
     * @param policy 策略管理
     * @return 策略管理
     */
    @Override
    public List<Policy> selectPolicyList(Policy policy)
    {
        return policyMapper.selectPolicyList(policy);
    }

    /**
     * 新增策略管理
     * 
     * @param policy 策略管理
     * @return 结果
     */
    @Override
    public int insertPolicy(Policy policy)
    {
        policy.setCreateTime(DateUtils.getNowDate());
        return policyMapper.insertPolicy(policy);
    }

    /**
     * 修改策略管理
     * 
     * @param policy 策略管理
     * @return 结果
     */
    @Override
    public int updatePolicy(Policy policy)
    {
        policy.setUpdateTime(DateUtils.getNowDate());
        return policyMapper.updatePolicy(policy);
    }

    /**
     * 批量删除策略管理
     * 
     * @param policyIds 需要删除的策略管理主键
     * @return 结果
     */
    @Override
    public int deletePolicyByPolicyIds(Long[] policyIds)
    {
        return policyMapper.deletePolicyByPolicyIds(policyIds);
    }

    /**
     * 删除策略管理信息
     * 
     * @param policyId 策略管理主键
     * @return 结果
     */
    @Override
    public int deletePolicyByPolicyId(Long policyId)
    {
        return policyMapper.deletePolicyByPolicyId(policyId);
    }
}
