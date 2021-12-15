package com.mylock.util;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.StrUtil;

/**
 * @Description:
 * @author: wujiawen
 * @date: 2021/9/28 13:58
 * @Version: 2.0
 */
public class Base64ImageUtils {

    private Base64ImageUtils(){}

    public static String getSuffix(String base64Str){
        String image = "image/";
        int begin = base64Str.indexOf(image) + image.length();
        int end = base64Str.indexOf(";");
        if(end <= begin){
            throw new ValidateException("异常的base64!");
        }
        String result = base64Str.substring(begin, end);
        if(StrUtil.isBlank(result)){
            throw new ValidateException("获取文件类型失败!");
        }
        return result;
    }

    /**
     * 截取base64前缀
     * @param base64Str base64文件前缀
     * @return String
     */
    public static String getBase64ForChange(String base64Str){
        return base64Str.substring(base64Str.indexOf(",") + 1);
    }
}
