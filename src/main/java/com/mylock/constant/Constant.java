package com.mylock.constant;

/**
 * 常量类
 */
public class Constant {

    private static final String PRODUCT = "PRODUCT";

    public static String productKey(Object id) {
        return PRODUCT + ":" + id;
    }

}
