package com.mylock.dto.shanghai;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 委托代理人信息
 * @author chaihao
 * @date 2021-12-23
 */
@Data
public class DLRXX {
    //申请编号
    @JSONField(name = "SQBH")
    private String SQBH;
    //当事人序号
    @JSONField(name = "DSRXH")
    private String DSRXH;
    //代理人序号
    @JSONField(name = "DLRXH")
    private String DLRXH;
    //代理人姓名
    @JSONField(name = "DLRMC")
    private String DLRMC;
    //性别
    @JSONField(name = "XB")
    private String XB;
    //	是否电子送达 1-是；0否
    @JSONField(name = "SFDZSD")
    private String SFDZSD;
    //联系电话
    @JSONField(name = "LXDH")
    private String LXDH;
    //代理人邮箱 是否电子送达为“是”时，必填
    @JSONField(name = "EMAIL")
    private String EMAIL;
    //证件类型
    @JSONField(name = "SFZM")
    private String SFZM;
    //证件号码
    @JSONField(name = "ZJHM")
    private String ZJHM;
    //地址
    @JSONField(name = "DZ")
    private String DZ;

}