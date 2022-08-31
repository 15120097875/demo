package com.mylock.dto.shanghai;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 尹志强
 * @title: BackCaseListBean
 * @description: 后端同步数据
 * @date 2021/11/30 13:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("受理信息")
public class BackCaseListBean extends WebCaseListBean{

    /**
     * 管辖法院
     */
    @ApiModelProperty("管辖法院名称")
    private String courtName;

    /**
     * 管辖法院
     */
    @ApiModelProperty("管辖法院编码")
    private String courtCode;

    /**
     * 调解机构名称
     */
    @ApiModelProperty("调解机构名称")
    private String orgInfoName;

    /**
     * 调解机构id
     */
    @ApiModelProperty("调解机构id")
    private String orgInfoId;

    /**
     * 当事人信息
     */
    @ApiModelProperty("当事人信息")
    private List<PartyBean> partyList;

    @ApiModelProperty(value = "交易pk", hidden = true)
    private Long tradeInfoId;

    @ApiModelProperty(value = "总罚息及复利（人民币）")
    private String totalInterest;

    @ApiModelProperty(value = "总本金（人民币）")
    private String totalLoanPrincipal;

    @ApiModelProperty(value = "总利息（人民币）")
    private String totalPenaltyInterest;

    @ApiModelProperty(value = "分期费用")
    private String installmentFees;

    @ApiModelProperty(value = "涉案店铺名称")
    private String SADPMC;

    @ApiModelProperty(value = "是否关停或查封")
    private String SFGTHCF;

    @ApiModelProperty(value = "涉案商品名称")
    private String SASPMC;

    @ApiModelProperty(value = "商品状态")
    private String SPZT;

    @ApiModelProperty(value = "销售情况")
    private String XSQK;

}
