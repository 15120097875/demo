package com.mylock.entity;

//import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Store  extends Model<Store> {

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
//    @TableId
    Integer pid;

    /**
     *  库存数量
     */
    Integer num;

}
