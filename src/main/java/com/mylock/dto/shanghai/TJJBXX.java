package com.mylock.dto.shanghai;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 调解基本信息
 * @author chaihao
 * @date 2021-12-23
 */
@Data
public class TJJBXX {
    //  申请编号
    @JSONField(name = "SQBH")
    private String SQBH;
    //  调解机构（调解中心）
    @JSONField(name = "TJZX")
    private String TJZX;
    //	调委会
    @JSONField(name = "TWH")
    private String TWH;
    //	联系电话
    @JSONField(name = "LXFS")
    private String LXFS;
    //	调解员
    @JSONField(name = "TJY")
    private String TJY;
    //	辅调解员 不是必填
    @JSONField(name = "FTJY")
    private String FTJY;
    //	办结日期
    @JSONField(name = "BJRQ")
    private String BJRQ;
    //	调解结果
    @JSONField(name = "TJJG")
    private String TJJG;
    //	调解状态
    @JSONField(name = "TJZT")
    private String TJZT;
    //	不受理原因
    @JSONField(name = "BSLYY")
    private String BSLYY;
    //	终止原因
    @JSONField(name = "ZZYY")
    private String ZZYY;
    //	退回原因
    @JSONField(name = "THYY")
    private String THYY;
    //	补充（正）材料名称
    @JSONField(name = "BZCL")
    private String BZCL;
    //	协议内容
    @JSONField(name = "XYNR")
    private String XYNR;
    //	无争议事实焦点
    @JSONField(name = "WZYSSJD")
    private String WZYSSJD;

}