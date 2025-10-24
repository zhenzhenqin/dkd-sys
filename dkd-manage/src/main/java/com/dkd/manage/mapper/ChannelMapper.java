package com.dkd.manage.mapper;

import java.util.List;
import com.dkd.manage.domain.Channel;
import com.dkd.manage.domain.dto.ChannelConfigDto;
import com.dkd.manage.domain.vo.ChannelVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 售货机货道Mapper接口
 * 
 * @author itheima
 * @date 2025-10-21
 */
public interface ChannelMapper 
{
    /**
     * 查询售货机货道
     * 
     * @param id 售货机货道主键
     * @return 售货机货道
     */
    public Channel selectChannelById(Long id);

    /**
     * 查询售货机货道列表
     * 
     * @param channel 售货机货道
     * @return 售货机货道集合
     */
    public List<Channel> selectChannelList(Channel channel);

    /**
     * 新增售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    public int insertChannel(Channel channel);

    /**
     * 修改售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    public int updateChannel(Channel channel);

    /**
     * 删除售货机货道
     * 
     * @param id 售货机货道主键
     * @return 结果
     */
    public int deleteChannelById(Long id);

    /**
     * 批量删除售货机货道
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChannelByIds(Long[] ids);

    /**
     * 批量新增售货机货道
     *
     * @param channelList
     */
    public void insertBatchChannel(List<Channel> channelList);

    /**
     * 根据商品ID查询关联货道数量
     * @param skuIds 商品ID数组
     * @return 关联货道数量
     */
    int countChannelBySkuIds(Long[] skuIds);

    /**
     * 根据售货机软编码查询货道信息
     *
     * @param innerCode 售货机软编码
     * @return 货道信息
     */
    public List<ChannelVo> selectChannelVoByInnerCode(String innerCode);

    /**
     * 根据售货机编码以及货道编码查询货道信息
     *
     * @param innerCode 售货机软编码
     * @param channelCode 货道编码
     * @return 货道信息
     */
    @Select("select * from tb_channel where inner_code = #{innerCode} and channel_code = #{channelCode}")
    public Channel getChannelInfo(@Param("innerCode") String innerCode,@Param("channelCode") String channelCode);

    /**
     * 批量修改货道
     *
     * @param list 货道信息列表
     * @return
     */
    public int batchUpdateChannel(List<Channel> list);
}
