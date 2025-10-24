package com.dkd.manage.domain.dto;

import lombok.Data;

//某个货道对应的sku信息
@Data
public class ChannelSkuDto {

    private String innerCode; //售货机内部编码（具体哪一台售货机）
    private String channelCode; //货道编码（具体哪一条货道）
    private Long skuId; //商品编号
}
