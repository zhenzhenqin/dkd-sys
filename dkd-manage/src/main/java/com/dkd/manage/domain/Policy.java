package com.dkd.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dkd.common.annotation.Excel;
import com.dkd.common.core.domain.BaseEntity;

/**
 * 策略管理对象 tb_policy
 * 
 * @author itheima
 * @date 2025-10-22
 */
public class Policy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 策略id */
    private Long policyId;

    /** 策略名称 */
    @Excel(name = "策略名称")
    private String policyName;

    /** 策略方案，如：80代表8折 */
    @Excel(name = "策略方案，如：80代表8折")
    private Long discount;

    public void setPolicyId(Long policyId) 
    {
        this.policyId = policyId;
    }

    public Long getPolicyId() 
    {
        return policyId;
    }
    public void setPolicyName(String policyName) 
    {
        this.policyName = policyName;
    }

    public String getPolicyName() 
    {
        return policyName;
    }
    public void setDiscount(Long discount) 
    {
        this.discount = discount;
    }

    public Long getDiscount() 
    {
        return discount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("policyId", getPolicyId())
            .append("policyName", getPolicyName())
            .append("discount", getDiscount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
