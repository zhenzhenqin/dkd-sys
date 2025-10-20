package com.dkd.manage.service.impl;

import java.util.List;
import com.dkd.common.utils.DateUtils;
import com.dkd.manage.mapper.RegionMapper;
import com.dkd.manage.mapper.RoleMapper;
import com.dkd.manage.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.EmpMapper;
import com.dkd.manage.domain.Emp;

/**
 * 人员列表Service业务层处理
 * 
 * @author itheima
 * @date 2025-10-20
 */
@Service
public class EmpServiceImpl implements EmpService
{
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询人员列表
     * 
     * @param id 人员列表主键
     * @return 人员列表
     */
    @Override
    public Emp selectEmpById(Long id)
    {
        return empMapper.selectEmpById(id);
    }

    /**
     * 查询人员列表列表
     * 
     * @param emp 人员列表
     * @return 人员列表
     */
    @Override
    public List<Emp> selectEmpList(Emp emp)
    {
        return empMapper.selectEmpList(emp);
    }

    /**
     * 新增人员列表
     * 
     * @param emp 人员列表
     * @return 结果
     */
    @Override
    public int insertEmp(Emp emp)
    {
        //补充区域信息 区域名 根据区域id去查询区域名称
        emp.setRegionName(regionMapper.selectRegionById(emp.getRegionId()).getRegionName());

        //补充相关角色信息
        //获取角色id
        Long roleId = emp.getRoleId();
        //角色名
        emp.setRoleName(roleMapper.selectRoleByRoleId(roleId).getRoleName());
        //角色编码
        emp.setRoleCode(roleMapper.selectRoleByRoleId(roleId).getRoleCode());

        emp.setCreateTime(DateUtils.getNowDate());
        return empMapper.insertEmp(emp);
    }

    /**
     * 修改人员列表
     * 
     * @param emp 人员列表
     * @return 结果
     */
    @Override
    public int updateEmp(Emp emp)
    {
        //补充区域信息 区域名 根据区域id去查询区域名称
        emp.setRegionName(regionMapper.selectRegionById(emp.getRegionId()).getRegionName());

        //补充相关角色信息
        //获取角色id
        Long roleId = emp.getRoleId();
        //角色名
        emp.setRoleName(roleMapper.selectRoleByRoleId(roleId).getRoleName());
        //角色编码
        emp.setRoleCode(roleMapper.selectRoleByRoleId(roleId).getRoleCode());

        emp.setUpdateTime(DateUtils.getNowDate());
        return empMapper.updateEmp(emp);
    }

    /**
     * 批量删除人员列表
     * 
     * @param ids 需要删除的人员列表主键
     * @return 结果
     */
    @Override
    public int deleteEmpByIds(Long[] ids)
    {
        return empMapper.deleteEmpByIds(ids);
    }

    /**
     * 删除人员列表信息
     * 
     * @param id 人员列表主键
     * @return 结果
     */
    @Override
    public int deleteEmpById(Long id)
    {
        return empMapper.deleteEmpById(id);
    }
}
