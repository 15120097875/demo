package com.mylock.dto.shanghai;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 2.2.4.调解材料信息
 * @author chaihao
 * @date 2021-12-23
 */
@Data
public class TJCL {
    //  申请编号
    @JSONField(name = "SQBH")
    private String SQBH;
    //  材料序号
    @JSONField(name = "CLXH")
    private String CLXH;
    //	材料来源
    @JSONField(name = "CLLY")
    private String CLLY;
    //	材料类型
    @JSONField(name = "CLLX")
    private String CLLX;
    //	材料明细
    @JSONField(name = "CLMX")
    private String CLMX;
    //	材料名称
    @JSONField(name = "CLMC")
    private String CLMC;
    //	材料内容
    @JSONField(name = "CLNR")
    private String CLNR;

}