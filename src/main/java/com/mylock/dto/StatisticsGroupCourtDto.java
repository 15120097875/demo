package com.mylock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StatisticsGroupCourtDto {

    @ApiModelProperty(value = "法院ID")
    private Long id;

    @ApiModelProperty(value = "法院名称")
    private String name;

    @ApiModelProperty(value = "拍辅机构数量")
    private Long companyCount;

    @ApiModelProperty(value = "已发起财产数量")
    private Long propertyCount;

    @ApiModelProperty(value = "已成交财产金额")
    private Long priceCount;

    @ApiModelProperty(value = "已完成用时")
    private Long timeCount;

    @ApiModelProperty(value = "已完成财产数量")
    private Long overPropertyCount;

    @ApiModelProperty(value = "已完成财产平均用时")
    private Integer averageTime;

}
