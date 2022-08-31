package com.mylock.dto.shanghai;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 *司法确认 一键起诉
 * @author chaihao
 * @date 2021-12-23
 */
@Data
@Builder
public class CourtSendRequest {
    //基本信息
    private AJJBXX AJJBXX;
    //诉讼材料
    private List<SSCL> SSCL;
    //当事人信息
    private List<DSRXX> DSRXX;
    //代理人信息
    private List<DLRXX> DLRXX;
    //银行账户信息
    private List<YHZHXX> YHZHXX;
}
