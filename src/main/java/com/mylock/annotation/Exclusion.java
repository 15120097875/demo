package com.mylock.annotation;

import java.lang.annotation.*;

/**
 *  @author liqing

 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Exclusion {

    /**
     * 0 1
     */
    String value() default "0";
}
