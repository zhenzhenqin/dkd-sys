package com.dkd.manage.service;

import java.util.List;
import com.dkd.manage.domain.VendingMachine;
import org.apache.ibatis.annotations.Select;

/**
 * 设备管理Service接口
 * 
 * @author itheima
 * @date 2025-10-21
 */
public interface IVendingMachineService 
{
    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    public VendingMachine selectVendingMachineById(Long id);

    /**
     * 查询设备管理列表
     * 
     * @param vendingMachine 设备管理
     * @return 设备管理集合
     */
    public List<VendingMachine> selectVendingMachineList(VendingMachine vendingMachine);

    /**
     * 新增设备管理
     * 
     * @param vendingMachine 设备管理
     * @return 结果
     */
    public int insertVendingMachine(VendingMachine vendingMachine);

    /**
     * 修改设备管理
     * 
     * @param vendingMachine 设备管理
     * @return 结果
     */
    public int updateVendingMachine(VendingMachine vendingMachine);

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键集合
     * @return 结果
     */
    public int deleteVendingMachineByIds(Long[] ids);

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    public int deleteVendingMachineById(Long id);

    /**
     * 根据售货机软编码查询售货机信息
     *
     * @param innerCode 售货机软编码
     * @return 售货机信息
     */
    public VendingMachine getVendingMachineByInnerCode(String innerCode);
}
