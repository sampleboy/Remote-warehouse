package com.ithima_04;

import java.util.Scanner;

public class StringTest2 {
    public static void main(String[] args) {

        //键盘录入录入数据
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串数据");
        //键盘接收数据
        String s = sc.nextLine();

        int bigcount = 0;
        int smallcount = 0;
        int numbercount = 0;


        for (int x = 0; x < s.length(); x++) {
            char ch = s.charAt(x);
            if (ch >= 'A' && ch <= 'Z') {
                bigcount++;
            } else if (ch >= 'a' && ch <= 'z') {
                smallcount++;
            } else if (ch >= '0' && ch <= '9') {
                numbercount++;
            } else {
                System.out.println("该字符非法");
            }
        }
        System.out.println("大写字符：" + bigcount);
        System.out.println("小写字符：" + smallcount);
        System.out.println("数字字符：" + numbercount);
    }
}
