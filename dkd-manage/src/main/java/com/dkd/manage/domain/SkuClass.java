package com.dkd.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dkd.common.annotation.Excel;
import com.dkd.common.core.domain.BaseEntity;

/**
 * 商品类型对象 tb_sku_class
 * 
 * @author itheima
 * @date 2025-10-22
 */
public class SkuClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long classId;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String className;

    /** 上级id */
    private Long parentId;

    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classId", getClassId())
            .append("className", getClassName())
            .append("parentId", getParentId())
            .toString();
    }
}
