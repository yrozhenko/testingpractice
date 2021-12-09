package com.solvd.testingpractice.utils;

import java.util.Random;

public class UsefulMethods {
    public static String getRandomValues(String charSet, int lengthOfKeys) {
        Random random = new Random();
        StringBuilder bld = new StringBuilder(lengthOfKeys);
        for (int i = 0; i < lengthOfKeys; i++) {
            bld.append(charSet.charAt(random.nextInt(charSet.length())));
        }

        return bld.toString();
    }
}
