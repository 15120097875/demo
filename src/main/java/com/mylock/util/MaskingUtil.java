package com.mylock.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

/**
 * @author 尹志强
 * @title: p
 * @description: 字符串脱敏工具类
 * @date 2021/10/11 23:51
 */
public class MaskingUtil {

    /**
     * 手机号脱敏
     * @param phone 待脱敏的手机号码
     * @return 脱敏完成的手机号码
     */
    public static String phoneMasking(String phone) {
        if (StringUtils.isBlank(phone)){
            return "";
        }
        String phone1 = phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
        return phone1;
    }

    /**
     * 身份证号码脱敏
     * @param idCard 待脱敏的身份证号码
     * @return 脱敏完成的身份证号码
     */
    public static String idCardMasking(String idCard){
        if (StringUtils.isBlank(idCard)){
            return "";
        }
        return idCard.substring(0,6) + "********" + idCard.substring(idCard.length() -4);
    }

    /**
     * base64加密
     * @param phone 手机号
     * @return 加密结果字符串
     */
    public static String phoneBase64(String phone){
        return StringUtils.isBlank(phone) ? "" : Base64Utils.encodeToString(phone.getBytes());
    }

    public static String  testEnc(String code) {
        // 构建前端对应解密AES 因子
        AES aes = new AES(Mode.CFB, Padding.NoPadding,
                new SecretKeySpec("pigxpigxpigxpigx".getBytes(), "AES"),
                new IvParameterSpec("pigxpigxpigxpigx".getBytes()));
        String password = aes.encryptBase64(code, Charset.defaultCharset());
//        Assertions.assertEquals("JFat0Zdc", password);
        return password;
    }

    public static String testDec(String code) {
        // 构建前端对应解密AES 因子
        AES aes = new AES(Mode.CFB, Padding.NoPadding,
                new SecretKeySpec("pigxpigxpigxpigx".getBytes(), "AES"),
                new IvParameterSpec("pigxpigxpigxpigx".getBytes()));
        String password = aes.decryptStr(code, Charset.defaultCharset());
        return password;
    }

}
