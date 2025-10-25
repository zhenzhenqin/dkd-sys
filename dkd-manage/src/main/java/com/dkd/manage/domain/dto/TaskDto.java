package com.dkd.manage.domain.dto;

import lombok.Data;

import java.util.List;

//前端接受工单信息
@Data
public class TaskDto {
    private String innerCode; //售货机编码
    private Long productTypeId; //工单类型编码
    private Long userId; //执行人id
    private String desc; //工单备注
    private Long assignorId;// 用户创建人id
    private Long createType;// 创建类型
    private List<TaskDetailsDto> details; //工单详情列表(只有补货的时候才有这个选项)
}
