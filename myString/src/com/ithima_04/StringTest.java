package com.ithima_04;

import java.util.Locale;

public class StringTest {
    public static void main(String[] args) {
        String s = "abcde";

        for (int x = 0; x < s.length(); x++) {
            System.out.println(s.charAt(x));
        }

        System.out.println("------------");
        System.out.println("HelloWorld".toLowerCase(Locale.ROOT));
        System.out.println("HelloWorld".toUpperCase(Locale.ROOT));
    }
}
