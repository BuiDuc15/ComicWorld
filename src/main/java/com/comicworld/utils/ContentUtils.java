package com.comicworld.utils;

import java.util.Map;

public class ContentUtils {

    public static String buildContentForOTPMail(Map<String, Object> params) {
        String otp = (String) params.get("otp");

        return "<p>Mã OTP: <b>" + otp + "<b/> (hết hạn trong vòng 5 phút)</p>" +
                "<p>Tuyệt đối <b>KHÔNG</b> chia sẻ mã OTP với bất kỳ ai để giữ an toàn cho tài khoản của bạn.";
    }

}
