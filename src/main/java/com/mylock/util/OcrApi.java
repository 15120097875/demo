package com.mylock.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class OcrApi {

    private static String CaptchaImageUrl = "http://s8.oncloudsoft.com/yzm-ocr/rec_yzm/";

    public static void setCaptchaImageUrl(String captchaImageUrl) {
        CaptchaImageUrl = captchaImageUrl;
    }

    /**
     * 验证码ocr
     *
     * @param imgBase64 验证码图像
     * @return 验证码
     */
    public static String ocrCaptchaImage(String imgBase64) {

        if (StrKit.isBlank(imgBase64)) {
            return "";
        }

        if (imgBase64.startsWith("data:image/png;base64,")) {
            imgBase64 = imgBase64.replaceFirst("data:image/png;base64,", "");
        }

        String data = String.format("{\"image_data\": \"%s\"}", imgBase64);

        try {
            String response = HttpKit.post(CaptchaImageUrl, data);

            JSONObject result = JSON.parseObject(response);

            if (Objects.isNull(result)) {
                return "";
            }

            String word = result.getString("word");

            return Objects.isNull(word) ? "" : word;
        } catch (Exception e) {
            log.error("验证码识别", e);
            return "";
        }
    }
}
