package com.mylock.constant;

import java.util.HashMap;
import java.util.Map;

public class GlobalConstant {

    private GlobalConstant(){}

    /**
     * 案件状态：待受理
     */
    public static final int CASE_STATUS_PENDING = 1;
    /**
     * 案件状态：调解中
     */
    public static final int CASE_STATUS_MEDIATING = 2;
    /**
     * 案件状态：待签字
     */
    public static final int CASE_STATUS_TO_BE_SIGN = 3;
    /**
     * 案件状态：调解成功
     */
    public static final int CASE_STATUS_SUCCESS = 4;
    /**
     * 案件状态：调解失败
     */
    public static final int CASE_STATUS_FAIL = 5;
    /**
     * 案件状态：待申请人签字
     */
    public static final int CASE_STATUS_FAIL_RECEIVE = 6;
    /**
     * 案件状态：被申请人已签订调解协议书 未签订承诺函
     */
    public static final int CASE_STATUS_FAIL_RECEIVE_AGREEMENT = 7;

    /**
     * 电子送达状态：待送达
     */
    public static final int E_STATUS_PENDING = 0;

    /**
     * 电子送达状态：已送达
     */
    public static final int E_STATUS_SUCCESS = 1;

    /**
     * 电子送达状态：送达中
     */
    public static final int E_STATUS_MEDIATING = 2;

    /**
     * 电子送达状态：送达失败
     */
    public static final int E_STATUS_FAIL = 3;

    /**
     * 案件数量正序
     */
    public static final int CASE_COUNT_ACE = 0;
    /**
     * 案件数量倒序
     */
    public static final int CASE_COUNT_DESC = 1;

    /**
     * 沟通失败原因：不同意任何形式调解
     */
    public static final int COMMUNICATE_FAILED_TYPE_ALL = 1;

    /**
     *  沟通失败原因：其他
     */
    public static final int COMMUNICATE_FAILED_TYPE_OTHER = 2;

    /**
     *  沟通电话状态：关机
     */
    public static final int COMMUNICATE_PHONE_TYPE_POWEROFF = 3;
    /**
     *  沟通电话状态：挂机
     */
    public static final int COMMUNICATE_PHONE_TYPE_HANGOFF = 4;
    /**
     *  沟通电话状态：停机
     */
    public static final int COMMUNICATE_PHONE_TYPE_SHUTDOWN = 5;

    /**
     * 沟通电话状态：接听-本人
     */
    public static final int COMMUNICATE_PHONE_TYPE_ANSER_ONESELF = 1;

    /**
     * 沟通电话状态：接听-非本人
     */
    public static final int COMMUNICATE_PHONE_TYPE_ANSER_NOTSELF = 2;
    /**
     * 沟通结果：同意调解
     */
    public static final int COMMUNICATE_RESULT_AGREE = 1;

    /**
     * 沟通结果：不同意调解
     */
    public static final int COMMUNICATE_RESULT_DISAGREE = 2;

    /**
     * redis待发送短信队列
     */
    public static final String REDIS_LIST_SENDMESSAGE = "LIST_MESSAGE_TO_SEND";
    /**
     * 模板替换值文本类型
     */
    public static final String CASE_TEMPLATE_TYPE_TEXT = "1";

    /**
     * 模板替换值图片类型
     */
    public static final String CASE_TEMPLATE_TYPE_PICTURE = "3";

    /**
     * 文件类型：调解协议
     */
    public static final int CASE_FILE_MEDIATE_AGREEMENT = 1;

    /**
     * 文件类型：调解笔录
     */
    public static final int CASE_FILE_MEDIATE_RECORD = 2;

    /**
     * 文件类型：通话录音
     */
    public static final int CASE_FILE_AUDIO = 3;

    /**
     * 文件类型：视频文件
     */
    public static final int CASE_FILE_VIDEO = 4;
    /**
     * 文件类型：承诺函
     */
    public static final int CASE_FILE_MEDIATE_COMMITMENT = 5;

    /**
     * 自增长序列号长度
     */
    public static final int INDEX_LENGTH = 7;
    /**
     * 调解协议调解员的签名标志
     */
    public static final String AGREEMENT_SIGN_USER = "userSgin";

    /**
     * 调解协议申请人的签名标志
     */
    public static final String AGREEMENT_SIGN_RECEIVE = "receiveSign";

    /**
     * 调解协议被告的签名标志
     */
    public static final String AGREEMENT_SIGN_APPLY = "applySign";
    /**
     * 调解协议书模板名称
     */
    public static final String AGREEMENT_TEMPLATE_NAME = "协调协议书.docx";
    /**
     * 调解协议书模板名称
     */
    public static final String COMMITMENT_TEMPLATE_NAME = "承诺函.docx";

    /**
     * 网易云信accid前缀
     */
    public static final String YUNXIN_ACCID_PREFIX = "odr_";

    /**
     * 有定稿
     */
    public static final String TEMPLATE_STATUS_YES = "1";
    /**
     * 没有定稿
     */
    public static final String TEMPLATE_STATUS_NO = "0";
    /**
     * 承诺函
     */
    public static final String TEMPLATE_TYPE_COMMITMENT = "1";
    /**
     * 协议书
     */
    public static final String TEMPLATE_TYPE_AGREEMENT = "0";


    /**
     * 沟通不同意原因MAP
     */
    private static final Map<Integer, String> COMMUNICATE_FAILED_TYPE_MAP = new HashMap<>(4);

    static {
        COMMUNICATE_FAILED_TYPE_MAP.put(COMMUNICATE_PHONE_TYPE_POWEROFF, "关机");
        COMMUNICATE_FAILED_TYPE_MAP.put(COMMUNICATE_PHONE_TYPE_HANGOFF, "挂机");
        COMMUNICATE_FAILED_TYPE_MAP.put(COMMUNICATE_PHONE_TYPE_SHUTDOWN, "停机");
        COMMUNICATE_FAILED_TYPE_MAP.put(COMMUNICATE_FAILED_TYPE_ALL, "不同意任何形式调解");
    }

    public static String getPhoneTypeStatus(int code){
        return COMMUNICATE_FAILED_TYPE_MAP.get(code);
    }

}
