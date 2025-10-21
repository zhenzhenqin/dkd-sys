package com.dkd.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.dkd.common.constant.DkdContants;
import com.dkd.common.utils.DateUtils;
import com.dkd.common.utils.uuid.UUIDUtils;
import com.dkd.manage.domain.Channel;
import com.dkd.manage.domain.Node;
import com.dkd.manage.domain.VmType;
import com.dkd.manage.mapper.ChannelMapper;
import com.dkd.manage.mapper.NodeMapper;
import com.dkd.manage.mapper.VmTypeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.VendingMachineMapper;
import com.dkd.manage.domain.VendingMachine;
import com.dkd.manage.service.IVendingMachineService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设备管理Service业务层处理
 * 
 * @author itheima
 * @date 2025-10-21
 */
@Service
public class VendingMachineServiceImpl implements IVendingMachineService 
{
    @Autowired
    private VendingMachineMapper vendingMachineMapper;
    @Autowired
    private VmTypeMapper vmTypeMapper;
    @Autowired
    private NodeMapper nodeMapper;
    @Autowired
    private ChannelMapper channelMapper;

    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    @Override
    public VendingMachine selectVendingMachineById(Long id)
    {
        return vendingMachineMapper.selectVendingMachineById(id);
    }

    /**
     * 查询设备管理列表
     * 
     * @param vendingMachine 设备管理
     * @return 设备管理
     */
    @Override
    public List<VendingMachine> selectVendingMachineList(VendingMachine vendingMachine)
    {
        return vendingMachineMapper.selectVendingMachineList(vendingMachine);
    }

    /**
     * 新增设备管理
     * 
     * @param vendingMachine 设备管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertVendingMachine(VendingMachine vendingMachine)
    {
        //1.新增设备
        //1.1 系统随机生成内部编号
        String innerCode = UUIDUtils.getUUID();
        vendingMachine.setInnerCode(innerCode);

        //1.2 设置最大容量 从设备类型中获取
         //根据设备类型id 获取到对应的设备类型
        VmType vmType = vmTypeMapper.selectVmTypeById(vendingMachine.getVmTypeId());
        vendingMachine.setChannelMaxCapacity(vmType.getChannelMaxCapacity()); //获取最大容量

        //1.3 设置详细地址 通过前端传入的点位id获取到具体的点位
        Node node = nodeMapper.selectNodeById(vendingMachine.getNodeId());
         //属性拷贝 设置详细地址，商圈类型，区域id，合作商id 但是需要将node中的id属性排除
        BeanUtils.copyProperties(node, vendingMachine, "id");
        vendingMachine.setAddr(node.getAddress());

        //1.4 设置设备状态 刚开始设置为未投放
        vendingMachine.setVmStatus(DkdContants.VM_STATUS_NODEPLOY);

        //设置创建时间
        vendingMachine.setCreateTime(DateUtils.getNowDate());
        vendingMachine.setUpdateTime(DateUtils.getNowDate());
        int result = vendingMachineMapper.insertVendingMachine(vendingMachine);

        //新增货道
        List<Channel> channelList = new ArrayList<>();
        for (int i = 0; i < vmType.getVmCol(); i++) { //遍历行
            for (int j = 0; j < vmType.getVmRow(); j++){ //遍历列   货道总数量 = 行数 * 列数
                Channel channel = new Channel();
                channel.setChannelCode(i + "-" + j); //设置货道编号 i-j
                channel.setVmId(vendingMachine.getId()); //设置售货机id
                channel.setInnerCode(vendingMachine.getInnerCode()); //设置售货机软编号
                channel.setMaxCapacity(vmType.getChannelMaxCapacity()); //设置货道最大容量

                //设置时间
                channel.setCreateTime(DateUtils.getNowDate());
                channel.setUpdateTime(DateUtils.getNowDate());

                channelList.add(channel);
            }
        }

        //批量插入货道
        channelMapper.insertBatchChannel(channelList);

        return result; //返回影响的行数
    }

    /**
     * 修改设备管理
     * 
     * @param vendingMachine 设备管理
     * @return 结果
     */
    @Override
    public int updateVendingMachine(VendingMachine vendingMachine)
    {
        //传入对的不仅需要更新点位 同时还需要更新冗余字段 包括区域的地址，区域id，合作商id以及商圈类型
            //先根据点位id获取点位
        Node node = nodeMapper.selectNodeById(vendingMachine.getNodeId());
            //属性拷贝 设置详细地址，商圈类型，区域id，合作商id 但是需要将node中的id属性排除
        BeanUtils.copyProperties(node, vendingMachine, "id");
        vendingMachine.setAddr(node.getAddress());
        vendingMachine.setUpdateTime(DateUtils.getNowDate());
        return vendingMachineMapper.updateVendingMachine(vendingMachine);
    }

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineByIds(Long[] ids)
    {
        return vendingMachineMapper.deleteVendingMachineByIds(ids);
    }

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineById(Long id)
    {
        return vendingMachineMapper.deleteVendingMachineById(id);
    }
}
