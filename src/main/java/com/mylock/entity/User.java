package com.mylock.entity;

//import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class User  extends Model<User> {

    /**
     * id
     */
//    @TableId
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 比例
     */
    private String bl;


}
