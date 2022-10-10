package com.comicworld.utils;

public class ConvertUtils {

    public static Long convertStringToLong(String text) {
        return Long.parseLong(text);
    }

    public static Long fromMinutesToMillis(Long minutes) {
        return minutes * 60 * 1000;
    }

}
