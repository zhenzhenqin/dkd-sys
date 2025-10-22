package com.dkd.manage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.SkuClassMapper;
import com.dkd.manage.domain.SkuClass;
import com.dkd.manage.service.ISkuClassService;

/**
 * 商品类型Service业务层处理
 * 
 * @author itheima
 * @date 2025-10-22
 */
@Service
public class SkuClassServiceImpl implements ISkuClassService 
{
    @Autowired
    private SkuClassMapper skuClassMapper;

    /**
     * 查询商品类型
     * 
     * @param classId 商品类型主键
     * @return 商品类型
     */
    @Override
    public SkuClass selectSkuClassByClassId(Long classId)
    {
        return skuClassMapper.selectSkuClassByClassId(classId);
    }

    /**
     * 查询商品类型列表
     * 
     * @param skuClass 商品类型
     * @return 商品类型
     */
    @Override
    public List<SkuClass> selectSkuClassList(SkuClass skuClass)
    {
        return skuClassMapper.selectSkuClassList(skuClass);
    }

    /**
     * 新增商品类型
     * 
     * @param skuClass 商品类型
     * @return 结果
     */
    @Override
    public int insertSkuClass(SkuClass skuClass)
    {
        return skuClassMapper.insertSkuClass(skuClass);
    }

    /**
     * 修改商品类型
     * 
     * @param skuClass 商品类型
     * @return 结果
     */
    @Override
    public int updateSkuClass(SkuClass skuClass)
    {
        return skuClassMapper.updateSkuClass(skuClass);
    }

    /**
     * 批量删除商品类型
     * 
     * @param classIds 需要删除的商品类型主键
     * @return 结果
     */
    @Override
    public int deleteSkuClassByClassIds(Long[] classIds)
    {
        return skuClassMapper.deleteSkuClassByClassIds(classIds);
    }

    /**
     * 删除商品类型信息
     * 
     * @param classId 商品类型主键
     * @return 结果
     */
    @Override
    public int deleteSkuClassByClassId(Long classId)
    {
        return skuClassMapper.deleteSkuClassByClassId(classId);
    }
}
