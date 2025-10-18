package com.dkd.manage.domain.vo;

import com.dkd.manage.domain.Partner;
import lombok.Data;

@Data
public class PartnerVo extends Partner {

    //合作商下的点位数
    private Integer nodeCount;
}
