package com.ithima_06;

public class StringDemon {
    public static void main(String[] args) {
        String s1 = "helloworld";
        String s2 = " helloworld  ";
        String s3 = "  hello  world  ";
        System.out.println(s2);
        System.out.println(s2.trim());


        String s4 = "aa,bb,cc";
        String[] strArray = s4.split(",");
        for (int x = 0; x < strArray.length; x++) {
            System.out.println(strArray[x]);
        }
    }
}
