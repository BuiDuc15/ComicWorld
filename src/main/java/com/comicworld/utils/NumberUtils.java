package com.comicworld.utils;

import java.util.Random;

public class NumberUtils {

    public static Long generateRandomNumber() {
        return Math.abs(new Random().nextLong());
    }

}
