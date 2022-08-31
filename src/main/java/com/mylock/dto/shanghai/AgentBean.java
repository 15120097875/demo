package com.mylock.dto.shanghai;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 尹志强
 * @title: AgentBean
 * @description: 代理人
 * @date 2021/12/4 12:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("代理人信息")
public class AgentBean {

    @ApiModelProperty(value = "当事人序号", hidden = true)
    @JsonIgnore
    private String partyNumber;

    @ApiModelProperty(value = "代理人姓名")
    private String name;

    @ApiModelProperty(value = "证件类型")
    private String cardType;

    @ApiModelProperty(value = "证件号码")
    private String cardNo;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "性别")
    private String sex;

}
