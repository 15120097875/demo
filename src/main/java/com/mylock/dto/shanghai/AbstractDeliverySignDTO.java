package com.mylock.dto.shanghai;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 送达接口签名和公共参数
 *
 * @author LEE
 * @date 2021-12-24
 */
@ApiModel("送达接口签名和公共参数")
@Data
public abstract class AbstractDeliverySignDTO {

    /**
     *
     */
    @ApiModelProperty(value = "生成规则md5(AccessID+timestamp)")
    private String key;

    /**
     *
     */
    @ApiModelProperty(value = "毫秒值")
    private Long timestamp;

    /**
     * 接口方法id
     *
     * @return
     */
    public abstract String getMethod();
}
