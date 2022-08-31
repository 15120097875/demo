package com.mylock.dto.shanghai;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 起诉材料
 * @author chaihao
 * @date 2021-12-23
 */
@Data
public class SSCL {
    //申请编号
    @JSONField(name = "SQBH")
    private String SQBH;
    //材料序号
    @JSONField(name = "XH")
    private String XH;
    //材料来源
    @JSONField(name = "CLLY")
    private String CLLY;
    //材料类型
    @JSONField(name = "CLLX")
    private String CLLX;
    //材料名称
    @JSONField(name = "CLMC")
    private String CLMC;
    //材料内容
    @JSONField(name = "CLNR")
    private String CLNR;

}