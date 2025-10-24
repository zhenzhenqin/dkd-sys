package com.dkd.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dkd.common.annotation.Excel;
import com.dkd.common.core.domain.BaseEntity;

/**
 * 工单详情对象 tb_task_details
 * 
 * @author itheima
 * @date 2025-10-24
 */
public class TaskDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long detailsId;

    /** 工单Id */
    @Excel(name = "工单Id")
    private Long taskId;

    /** 货道编号 */
    @Excel(name = "货道编号")
    private String channelCode;

    /** 补货期望容量 */
    @Excel(name = "补货期望容量")
    private Long expectCapacity;

    /** 商品Id */
    @Excel(name = "商品Id")
    private Long skuId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String skuName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String skuImage;

    public void setDetailsId(Long detailsId) 
    {
        this.detailsId = detailsId;
    }

    public Long getDetailsId() 
    {
        return detailsId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setExpectCapacity(Long expectCapacity) 
    {
        this.expectCapacity = expectCapacity;
    }

    public Long getExpectCapacity() 
    {
        return expectCapacity;
    }
    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }
    public void setSkuName(String skuName) 
    {
        this.skuName = skuName;
    }

    public String getSkuName() 
    {
        return skuName;
    }
    public void setSkuImage(String skuImage) 
    {
        this.skuImage = skuImage;
    }

    public String getSkuImage() 
    {
        return skuImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailsId", getDetailsId())
            .append("taskId", getTaskId())
            .append("channelCode", getChannelCode())
            .append("expectCapacity", getExpectCapacity())
            .append("skuId", getSkuId())
            .append("skuName", getSkuName())
            .append("skuImage", getSkuImage())
            .toString();
    }
}
