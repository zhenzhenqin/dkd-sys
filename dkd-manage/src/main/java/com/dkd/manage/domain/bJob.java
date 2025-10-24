package com.dkd.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dkd.common.annotation.Excel;
import com.dkd.common.core.domain.BaseEntity;

/**
 * 自动补货任务对象 tb_job
 * 
 * @author itheima
 * @date 2025-10-24
 */
public class bJob extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 警戒值百分比 */
    @Excel(name = "警戒值百分比")
    private Long alertValue;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAlertValue(Long alertValue) 
    {
        this.alertValue = alertValue;
    }

    public Long getAlertValue() 
    {
        return alertValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("alertValue", getAlertValue())
            .toString();
    }
}
