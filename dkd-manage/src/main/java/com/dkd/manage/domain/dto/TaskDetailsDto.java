package com.dkd.manage.domain.dto;

import lombok.Data;

//工单详情
@Data
public class TaskDetailsDto {

    private String channelCode;// 货道编号
    private Long expectCapacity;// 期望补货数量
    private Long skuId;// 商品Id
    private String skuName;// 商品名称
    private String skuImage;// 商品图片
}