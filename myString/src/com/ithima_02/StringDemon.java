package com.ithima_02;

import java.util.SplittableRandom;

public class StringDemon {
    public static void main(String[] args) {
        String s1 = new String("hello");
        System.out.println("s1:" + s1);
        System.out.println("-------------");

        char[] chs = {'h', 'e', 'l', 'l', 'w'};
        String s2 = new String(chs);
        System.out.println("s2:" + s2);
        System.out.println("------------");

        String s3 = new String(chs, 1, 3);
        System.out.println("s3:" + s3);
    }
}
