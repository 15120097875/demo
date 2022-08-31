package com.mylock.dto.shanghai;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 2.2.3.调解记录信息
 * @author chaihao
 * @date 2021-12-23
 */
@Data
public class TJJL {
    //  申请编号
    @JSONField(name = "SQBH")
    private String SQBH;
    //  调解序号（次数）
    @JSONField(name = "TJXH")
    private String TJXH;
    //	调解时间
    @JSONField(name = "TJSJ")
    private String TJSJ;
    //	调解地点
    @JSONField(name = "TJDD")
    private String TJDD;
    //	调解记录
    @JSONField(name = "TJJL")
    private String TJJL;
    //	调解结果
    @JSONField(name = "TJJG")
    private String TJJG;
}