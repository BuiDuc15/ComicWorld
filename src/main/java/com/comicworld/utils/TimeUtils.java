package com.comicworld.utils;

import java.time.Instant;

public class TimeUtils {

    public static Long getCurrentUTCTimeInMilliseconds() {
        return Instant.now().toEpochMilli();
    }

    public static Long fromMinutesToMillis(Long minutes) {
        return minutes * 60 * 1000;
    }

}
