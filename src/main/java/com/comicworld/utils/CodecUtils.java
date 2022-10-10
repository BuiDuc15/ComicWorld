package com.comicworld.utils;


import java.util.Base64;

public class CodecUtils {

    public static String decodeBase64EncodedString(String encodedString) {
        return new String(Base64.getDecoder().decode(encodedString));
    }

}
