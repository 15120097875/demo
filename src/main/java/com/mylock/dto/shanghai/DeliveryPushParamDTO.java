package com.mylock.dto.shanghai;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 推送电子送达接口参数
 *
 * @author LEE
 * @date 2021-12-24
 */
@ApiModel("推送电子送达接口参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class DeliveryPushParamDTO extends AbstractDeliverySignDTO {

    @ApiModelProperty(value = "发送信息id,主键")
    private String fsxxid;

    @ApiModelProperty(value = "案号")
    private String ah;

    @ApiModelProperty(value = "发送时间")
    private String fssj;

    @ApiModelProperty(value = "当事人名称")
    private String dsrmc;

    @ApiModelProperty(value = "当事人类型")
    private String lx;

    @ApiModelProperty(value = "身份证")
    private String sfz;

    /**
     * 社会信用代码\企业编号 当事人类型为组织时，两者至少有一项不必传
     *
     */
    @ApiModelProperty(value = "社会信用代码")
    private String shxydm;

    @ApiModelProperty(value = "企业编号")
    private String qybh;

    @ApiModelProperty(value = "法院代码")
    private String fybm;

    @ApiModelProperty(value = "受送达人")
    private String ssdr;

    @ApiModelProperty(value = "受送达人手机号码")
    private String ssdrsjhm;

    @ApiModelProperty(value = "邮箱")
    @JSONField(name = "dz_email")
    private String dzEmail;

    @ApiModelProperty(value = "证件类型 1：身份证、255：其他")
    private String zjlx;

    @ApiModelProperty(value = "是否同意电子送达 1：同意、0不同意")
    private String sfqrsjhm;

    @ApiModelProperty(value = "案由名称")
    private String aymc;

    @ApiModelProperty(value = "送达文书列表")
    private List<TextInfoFile> fileList;

    @Override
    public String getMethod() {
        return "setDzsd";
    }

    /**
     * 文书列表
     */
    @Data
    public static class TextInfoFile {

        @ApiModelProperty(value = "文书名称")
        private String wsmc;

        @ApiModelProperty(value = "文书id")
        private String wsid;

        @ApiModelProperty(value = "文书地址")
        private String url;

        @ApiModelProperty(value = "文书生成时间")
        private String createdate;

    }

}
