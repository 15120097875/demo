package com.mylock.dto.shanghai;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 尹志强
 * @description: 案件列表返回值
 * @date 2021/11/30 11:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("案件列表信息")
public class WebCaseListBean {

    /**
     * 原告
     */
    @ApiModelProperty("起诉人")
    private String accuserName;

    /**
     * 被告
     */
    @ApiModelProperty("被起诉人")
    private String accusedName;

    /**
     * 纠纷类型名称
     */
    @ApiModelProperty("纠纷类型名称")
    private String caseTypeName;

    /**
     * 纠纷类型id
     */
    @ApiModelProperty("纠纷类型id")
    private String caseTypeId;

    /**
     * 调解员
     */
    @ApiModelProperty("调解员名称")
    private String mediatorName;

    /**
     * 委派时间
     */
    @ApiModelProperty("委派时间")
    private String allocateTime;

    /**
     * 标的金额
     */
    @ApiModelProperty(value = "标的金额")
    private String targetAmount;

    /**
     * 案件标识
     */
    @ApiModelProperty(value = "案件标识")
    private String caseInfoId;

}
