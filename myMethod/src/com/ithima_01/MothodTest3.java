package com.ithima_01;

import java.util.Scanner;

public class MothodTest3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("请输入第一个数据");
        int x = sc.nextInt();
        System.out.println("请输入第二个数据：");
        int y = sc.nextInt();
        System.out.println("请输入第三个数据：");
        int z = sc.nextInt();


        int m = getMax(x, y, z);
        System.out.println("m:" + m);
    }


    public static int getMax(int a, int b, int c) {
        if (a > b) {
            if (a > c) {
                return a;
            } else {
                return c;
            }
        } else {
            if (b > c) {
                return b;
            } else {
                return c;
            }
        }
    }
}
