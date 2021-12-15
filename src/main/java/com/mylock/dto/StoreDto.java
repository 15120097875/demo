package com.mylock.dto;

import com.mylock.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto extends Store {

    /**
     * 增减量
     */
    @NotNull(message = "购买数量不能为空")
    private Integer delta;

    public StoreDto(Integer pid, Integer delta) {
        super.setPid(pid);
        this.delta = delta;
    }

}
