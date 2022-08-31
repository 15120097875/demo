package com.mylock.dto.shanghai;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 当事人信息
 * @author chaihao
 * @date 2021-12-23
 */
@Data
public class DSRXX {
    //	申请编号
    @JSONField(name = "SQBH")
    private String SQBH;
    //	当事人序号
    @JSONField(name = "DSRXH")
    private String DSRXH;
    //	当事人名称
    @JSONField(name = "DSRMC")
    private String DSRMC;
    //	当事人类别
    @JSONField(name = "DSRLB")
    private String DSRLB;
    //	当事人类型
    @JSONField(name = "DSRLX")
    private String DSRLX;
    //	性别 当事人类型为“个人”时必输
    @JSONField(name = "XB")
    private String XB;
    //	出生日期 当事人类型为“个人”时必输
    @JSONField(name = "CSRQ")
    private String CSRQ;
    //	法定代表人 当事人类型为“单位”时必输
    @JSONField(name = "FDDB")
    private String FDDB;
    //	是否电子送达 1-是；0否
    @JSONField(name = "SFDZSD")
    private String SFDZSD;
    //	联系电话
    @JSONField(name = "LXDH")
    private String LXDH;
    //	邮箱
    @JSONField(name = "EMAIL")
    private String EMAIL;
    //	证件类型
    @JSONField(name = "SFZM")
    private String SFZM;
    //	企业编号
    @JSONField(name = "QYBH")
    private String QYBH;
    //	证件号码
    @JSONField(name = "ZJHM")
    private String ZJHM;
    //	国籍
    @JSONField(name = "GJ")
    private String GJ;
    //	民族
    @JSONField(name = "MZ")
    private String MZ;
    //	联系地址
    @JSONField(name = "DZ")
    private String DZ;

}