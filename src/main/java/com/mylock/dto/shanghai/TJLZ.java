package com.mylock.dto.shanghai;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 2.2.5.调解流转信息
 * @author chaihao
 * @date 2021-12-23
 */
@Data
public class TJLZ {
    //  申请编号
    @JSONField(name = "SQBH")
    private String SQBH;
    //  状态
    @JSONField(name = "ZT")
    private String ZT;
    //	操作时间
    @JSONField(name = "SJ")
    private String SJ;
    //	操作单位
    @JSONField(name = "CZDW")
    private String CZDW;
    //	操作人
    @JSONField(name = "CZR")
    private String CZR;
    //	当前单位
    @JSONField(name = "DQDW")
    private String DQDW;
    //	负责人
    @JSONField(name = "FZR")
    private String FZR;
    //	描述
    @JSONField(name = "MS")
    private String MS;


}