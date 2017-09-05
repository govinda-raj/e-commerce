package com.merchant.utilities;

import java.util.Random;

/**
 * Created by coviam on 13/08/17.
 */
public class Utils {

    public static String generateRandomString(){
        Random r = new Random(); // just create one and keep it around
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";

        final int N = 8;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        String randomName = sb.toString();
        return randomName;
    }
}
