package com.dkd.manage.domain.dto;

import lombok.Data;

import java.util.List;

//售货机货道配置信息
@Data
public class ChannelConfigDto {
    private String innerCode; //售货机软编码
    private List<ChannelSkuDto> channelList; //货道对应的商品信息列表
}
