package com.ithima_01;

import java.util.Scanner;

public class ScannerDemon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入一个字符串数据：");

        String s = sc.nextLine();

        System.out.println("s:" + s);
    }
}
