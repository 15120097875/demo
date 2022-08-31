package com.mylock.dto.shanghai;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 拉取电子送达接口参数
 *
 * @author LEE
 * @date 2021-12-24
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = true)
public class DeliveryPullParamDTO extends AbstractDeliverySignDTO {

    /**
     * 发送信息id,主键
     */
    @ApiModelProperty(value = "发送信息id,主键")
    private String fsxxid;

    @Override
    public String getMethod() {
        return "getsdhz";
    }
}
