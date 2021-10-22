package com.ithima_04;

public class StringDemon {
    public static void main(String[] args) {
        //创建字符串对象
        String s = "helloworld";


        //int length()：获取字符串长度，其实也就是字符的个数
        System.out.println(s.length());
        System.out.println("-----------");


        System.out.println(s.charAt(0));
        System.out.println(s.charAt(1));
        System.out.println("-----------");


        System.out.println(s.indexOf("l"));
        System.out.println(s.indexOf("owo"));
        System.out.println(s.indexOf("ak"));
        System.out.println("----------");


        System.out.println(s.substring(0));
        System.out.println(s.substring(5));
        System.out.println("---------------");


        System.out.println(s.substring(0, s.length()));
        System.out.println(s.substring(3, 8));


    }
}
