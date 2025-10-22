package com.dkd.manage.service;

import java.util.List;
import com.dkd.manage.domain.SkuClass;

/**
 * 商品类型Service接口
 * 
 * @author itheima
 * @date 2025-10-22
 */
public interface ISkuClassService 
{
    /**
     * 查询商品类型
     * 
     * @param classId 商品类型主键
     * @return 商品类型
     */
    public SkuClass selectSkuClassByClassId(Long classId);

    /**
     * 查询商品类型列表
     * 
     * @param skuClass 商品类型
     * @return 商品类型集合
     */
    public List<SkuClass> selectSkuClassList(SkuClass skuClass);

    /**
     * 新增商品类型
     * 
     * @param skuClass 商品类型
     * @return 结果
     */
    public int insertSkuClass(SkuClass skuClass);

    /**
     * 修改商品类型
     * 
     * @param skuClass 商品类型
     * @return 结果
     */
    public int updateSkuClass(SkuClass skuClass);

    /**
     * 批量删除商品类型
     * 
     * @param classIds 需要删除的商品类型主键集合
     * @return 结果
     */
    public int deleteSkuClassByClassIds(Long[] classIds);

    /**
     * 删除商品类型信息
     * 
     * @param classId 商品类型主键
     * @return 结果
     */
    public int deleteSkuClassByClassId(Long classId);
}
