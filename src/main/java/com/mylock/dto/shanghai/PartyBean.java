package com.mylock.dto.shanghai;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 尹志强
 * @description: 当事人信息
 * @date 2021/11/30 14:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("当事人信息")
public class PartyBean {

    @ApiModelProperty(value = "当事人序号", hidden = true)
    @JsonIgnore
    private String partyNumber;

    /**
     * 当事人名称
     */
    @ApiModelProperty("当事人名称")
    private String name;

    /**
     * 当事人类别 起诉人 被起诉人 第三人
     */
    @ApiModelProperty("当事人类别(起诉人/ 被起诉人/ 第三人)")
    private String category;

    /**
     * 当事人类型 个人 单位
     */
    @ApiModelProperty("当事人类型(个人/ 单位)")
    private String type;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phone;

    /**
     * 证件类型 居民身份证 军官证 护照 其他
     */
    @ApiModelProperty("证件类型(居民身份证/ 军官证/ 护照/ 其他")
    private String cardType;

//    /**
//     * 企业编码
//     */
//    @ApiModelProperty("企业编码")
//    private String CompanyCode;

    /**
     * 证件号码
     */
    @ApiModelProperty("证件号码")
    private String cardNo;

    /**
     * 国籍
     */
    @ApiModelProperty("国籍")
    private String state;

    /**
     * 民族
     */
    @ApiModelProperty("民族")
    private String nationality;

    /**
     * 地址
     */
    @ApiModelProperty("联系地址")
    private String address;

    @ApiModelProperty("当事人邮箱")
    private String email;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("出生年月")
    private String birthday;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("统一社会信用代码")
    private String uscc; //英文首字母缩写

    @ApiModelProperty("法定代表人")
    private String legalRepresentative;

    @ApiModelProperty("法定代表人职务")
    private String legalRepresentativeDuty;

    @ApiModelProperty("户籍地")
    private String permanentResidenceAddress;

    @ApiModelProperty("代理人信息")
    private List<AgentBean> agentList;
}
