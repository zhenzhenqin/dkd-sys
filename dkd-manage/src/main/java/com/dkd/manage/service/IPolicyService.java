package com.dkd.manage.service;

import java.util.List;
import com.dkd.manage.domain.Policy;

/**
 * 策略管理Service接口
 * 
 * @author itheima
 * @date 2025-10-22
 */
public interface IPolicyService 
{
    /**
     * 查询策略管理
     * 
     * @param policyId 策略管理主键
     * @return 策略管理
     */
    public Policy selectPolicyByPolicyId(Long policyId);

    /**
     * 查询策略管理列表
     * 
     * @param policy 策略管理
     * @return 策略管理集合
     */
    public List<Policy> selectPolicyList(Policy policy);

    /**
     * 新增策略管理
     * 
     * @param policy 策略管理
     * @return 结果
     */
    public int insertPolicy(Policy policy);

    /**
     * 修改策略管理
     * 
     * @param policy 策略管理
     * @return 结果
     */
    public int updatePolicy(Policy policy);

    /**
     * 批量删除策略管理
     * 
     * @param policyIds 需要删除的策略管理主键集合
     * @return 结果
     */
    public int deletePolicyByPolicyIds(Long[] policyIds);

    /**
     * 删除策略管理信息
     * 
     * @param policyId 策略管理主键
     * @return 结果
     */
    public int deletePolicyByPolicyId(Long policyId);
}
