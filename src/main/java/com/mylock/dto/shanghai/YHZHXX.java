package com.mylock.dto.shanghai;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 银行账户信息
 * @author chaihao
 * @date 2021-12-23
 */
@Data
public class YHZHXX {
    //申请编号
    @JSONField(name = "SQBH")
    private String SQBH;
    //当事人序号
    @JSONField(name = "DSRXH")
    private String DSRXH;
    //开户行
    @JSONField(name = "KHH")
    private String KHH;
    //开户行名称
    @JSONField(name = "KHHMC")
    private String KHHMC;
    //开户支行
    @JSONField(name = "KHZH")
    private String KHZH;
    //	开户人
    @JSONField(name = "KHR")
    private String KHR;
    //银行账号
    @JSONField(name = "银行账号")
    private String 银行账号;


}