package com.mylock.dto.shanghai;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 案件基本信息
 *  * @author chaihao
 *  * @date 2021-12-23
 */
@Data
public class AJJBXX {
    //  申请编号
    @JSONField(name = "SQBH")
    private String SQBH;
    //  申请类型 1、司法确认；2申请法院出具调解书
    @JSONField(name = "SQLX")
    private String SQLX;
    //	受理法院
    @JSONField(name = "FYDM")
    private String FYDM;
    //	“申请类型”为“当事人由法院多元解纷平台申请调解的‘一键起诉’”时必填   目前待定传还是不传
    @JSONField(name = "DSRSQBH")
    private String DSRSQBH;
    //	调解机构
    @JSONField(name = "TJZX")
    private String TJZX;
    //	调委会
    @JSONField(name = "TWH")
    private String TWH;
    //	调解员
    @JSONField(name = "TJY")
    private String TJY;
    //	调解员联系方式
    @JSONField(name = "LXFS")
    private String LXFS;
    //	纠纷类型
    @JSONField(name = "JFLX")
    private String JFLX;
    //	申请日期
    @JSONField(name = "SQRQ")
    private String SQRQ;
    //	标的金额
    @JSONField(name = "BDJE")
    private String BDJE;
    //	调解结果
    @JSONField(name = "TJJG")
    private String TJJG;
    //	诉讼请求
    @JSONField(name = "SQSX")
    private String SQSX;
    //	事实与理由
    @JSONField(name = "ZYSS")
    private String ZYSS;
    //	协议内容
    @JSONField(name = "XYNR")
    private String XYNR;
    //	来源
    @JSONField(name = "QRLX")
    private String QRLX;
    //	是否接受诉前调  1-是；0-否   （前置化解需要跟用户沟通吗？）
    @JSONField(name = "SFJSSQT")
    private String SFJSSQT;

}