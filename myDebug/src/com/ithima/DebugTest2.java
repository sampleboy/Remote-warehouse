package com.ithima;

import java.util.Scanner;

public class DebugTest2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个数据：");
        int a = sc.nextInt();
        System.out.println("请输入第二个数据：");
        int b = sc.nextInt();


        int result = sum(a, b);
        System.out.println("result:" + result);


    }

    public static int sum(int a, int b) {
        int c = a + b;
        return c;
    }
}
