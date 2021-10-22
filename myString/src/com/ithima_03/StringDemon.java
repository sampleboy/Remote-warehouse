package com.ithima_03;

public class StringDemon {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "Hello";


        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println("-------------");

        System.out.println(s1.equalsIgnoreCase(s2));
        System.out.println(s1.equalsIgnoreCase(s3));
        System.out.println("-----------");
    }
}
