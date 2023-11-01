package com.mylock.entity;

//import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserFenan extends Model<UserFenan> {

    private Integer caseNum;
    private BigDecimal case_price;
    private String userName;


}
