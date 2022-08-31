package com.mylock.dto.shanghai;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 2.2.调解机构反馈当事人申请调解办理
 * @author chaihao
 * @date 2021-12-23
 */
@Data
@Builder
public class CourtFeedBack {
    //基本信息
    private TJJBXX TJJBXX;
    //2.2.3.调解记录信息
    private List<TJJL> TJJL;
    //2.2.4.调解材料信息
    private List<TJCL> TJCL;
    //2.2.5.调解流转信息
    private List<TJLZ> TJLZ;

}
