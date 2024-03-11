package com.mylock.vo;

import lombok.Data;

@Data
public class CustomQueryBaseVo {

    /**
     * 起始值
     */
    private String start;

    /**
     * 终止值
     */
    private String end;

    /**
     * 数值
     */
    private String text;

    /**
     * 是否填写 1-已填写 0-未填写
     */
    private String isValue;

    /**
     * 1-input-文本框 （富文本框）
     * 2-radio-下拉/单选框
     * 3-check-多(复)选框
     * 4 -金额输入框
     * 5-日期输入框
     * 6-地区输入框
     * 7-textarea-区间输入框
     * 8-逻辑输入框
     * 9-逻辑下拉框
     */
    private String inputType;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 所属表
     */
    private String tableName;
}
