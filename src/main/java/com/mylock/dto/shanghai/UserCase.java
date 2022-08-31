package com.mylock.dto.shanghai;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 人员案件分配表
 * @author
 * @date 2021-09-16 09:04:39
 */
@Data
@TableName("odr_user_case")
@ApiModel(value = "人员案件分配表")
public class UserCase{
    private static final long serialVersionUID = 1L;

    /**
     * 调解组织ID
     */
    @ApiModelProperty(value="调解组织ID")
    private Integer deptId;
    /**
     * 交易号
     */
    @ApiModelProperty(value="交易号")
    private Long tradeId;
    /**
     * 案件ID
     */
    @ApiModelProperty(value="案件ID")
    private Long caseId;
    /**
     * 调解员ID
     */
    @ApiModelProperty(value="调解员ID")
    private Integer userId;
    /**
     * 调解员姓名
     */
    @ApiModelProperty(value="调解员姓名")
    private String mediateName;
    /**
     * 申请人id
     */
    @ApiModelProperty(value="申请人id")
    private Integer applyId;
    /**
     * 申请人姓名
     */
    @ApiModelProperty(value="申请人姓名")
    private String applyName;
    /**
     * 状态： 1-待受理 2-调解中 3-待签字 4-调解成功 5-调解失败
     */
    @ApiModelProperty(value="状态： 1-待受理 2-调解中 3-待签字 4-调解成功 5-调解失败")
    private Integer state;
    /**
     * 短信状态：1-未发送 2-已发送 3-已送达
     */
    @ApiModelProperty(value="短信状态：1-未发送 2-已发送 3-已送达")
    private Integer messageState;
    /**
     * 申请人签字ID
     */
    @ApiModelProperty(value="申请人签字ID")
    private Long applySignId;
    /**
     * 被申请人签字ID
     */
    @ApiModelProperty(value="被申请人签字ID")
    private Long receiveSignId;

    /**
     * 送达时间
     */
    @ApiModelProperty(value="送达时间")
    private Long arriveTime;

    @ApiModelProperty(value = "分配时间")
    private Long allotTime;

    @ApiModelProperty(value = "失败原因")
    private String failedReason;

    @ApiModelProperty(value = "调解号")
    private String mediateNo;

    @ApiModelProperty(value = "受理时间")
    private Long acceptTime;

    @ApiModelProperty(value = "调解完成时间(含成功和失败)")
    private Long completeTime;

    /**
     * 统计数量
     */
    @ApiModelProperty(value="统计数量")
    @TableField(exist = false)
    private Integer num;

    /**
     * 电子送达状态：0-待送达 1-已送达 2-送达失败
     */
    @ApiModelProperty(value="电子送达状态：0-待送达 1-已送达 2-送达中 3-送达失败")
    private Integer edelivery;
    @ApiModelProperty(value="最后一次发起电子送达时间")
    private Long deliverySendTime;
    @ApiModelProperty(value="更新送达条件", hidden = true)
    @TableField(exist = false)
    private Integer updateDeliveryCondition;

    /**
     * 调解类型（1-视频调解，2-异步调解，3-现场调解，4-电话调解）
     */
    @ApiModelProperty(value="调解类型（1-视频调解，2-异步调解，3-现场调解，4-电话调解）")
    private Integer mediateType;

    /**
     * 调解次数
     */
    @ApiModelProperty(value="调解次数")
    private Integer mediateCount;
    /**
     * 司法确认（1-待申请，2-申请中，3-已确认）
     */
    @ApiModelProperty(value="司法确认（1-待申请，2-申请中，3-已确认）")
    private Integer judicialType;
    @ApiModelProperty(value="司法确认申请时间")
    private Long judicialApplytime;
    @ApiModelProperty(value="司法确认时间")
    private Long judicialConfirmtime;
    /**
     * 退回状态（1-未退回，2-已退回）
     */
    @ApiModelProperty(value="退回状态（1-未退回，2-已退回）")
    private Integer returnType;

    /**
     * 所属法院编码
     */
    @ApiModelProperty(value = "所属法院编码")
    private String courtCode;

    /**
     * 所属法院名称
     */
    @ApiModelProperty(value = "所属法院名称")
    private String courtName;
    /**
     * 调解阶段(1.送达阶段  2.化解阶段  3.签字阶段)
     */
    @ApiModelProperty(value="调解阶段(1.送达阶段  2.化解阶段  3.签字阶段)")
    private Integer mediationStage;

}
