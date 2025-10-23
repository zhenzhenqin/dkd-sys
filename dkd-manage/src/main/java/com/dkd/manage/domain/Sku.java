package com.dkd.manage.domain;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dkd.common.annotation.Excel;
import com.dkd.common.core.domain.BaseEntity;

/**
 * 商品管理对象 tb_sku
 * 
 * @author itheima
 * @date 2025-10-22
 */
@ExcelIgnoreUnannotated //忽略未注解的字段
@ColumnWidth(16) //设置列宽
@HeadRowHeight(14) //设置行高
@HeadFontStyle(fontHeightInPoints = 11) //设置字体大小
public class Sku extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long skuId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    @ExcelProperty("商品名称")
    private String skuName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    @ExcelProperty("商品图片")
    private String skuImage;

    /** 品牌 */
    @Excel(name = "品牌")
    @ExcelProperty("品牌")
    private String brandName;

    /** 规格(净含量) */
    @Excel(name = "规格(净含量)")
    @ExcelProperty("规格(净含量)")
    private String unit;

    /** 商品价格，单位分 */
    @Excel(name = "商品价格，单位分")
    @ExcelProperty("商品价格，单位分")
    private Long price;

    /** 商品类型Id */
    @Excel(name = "商品类型Id")
    @ExcelProperty("商品类型Id")
    private Long classId;

    /** 是否打折促销 */
    @Excel(name = "是否打折促销")
    private Integer isDiscount;

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
    public void setBrandName(String brandName) 
    {
        this.brandName = brandName;
    }

    public String getBrandName() 
    {
        return brandName;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setIsDiscount(Integer isDiscount) 
    {
        this.isDiscount = isDiscount;
    }

    public Integer getIsDiscount() 
    {
        return isDiscount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("skuId", getSkuId())
            .append("skuName", getSkuName())
            .append("skuImage", getSkuImage())
            .append("brandName", getBrandName())
            .append("unit", getUnit())
            .append("price", getPrice())
            .append("classId", getClassId())
            .append("isDiscount", getIsDiscount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
