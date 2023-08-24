package com.mylock.entity;

//import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class Aj extends Model<Aj> {


    /**
     * 用户名
     */
    private String ah;

    /**
     * 性别
     */
    private String rq;



}
