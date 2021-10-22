package com.ithima_05;

import java.util.Locale;
import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String s = sc.nextLine();


        String s1 = s.substring(0, 1);
        String s2 = s.substring(1);

        String s3 = s1.toUpperCase(Locale.ROOT) + s2.toLowerCase(Locale.ROOT);
        System.out.println("s3:" + s3);
    }
}
