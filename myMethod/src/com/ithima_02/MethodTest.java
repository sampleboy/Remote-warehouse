package com.ithima_02;

public class MethodTest {
    public static void main(String[] args) {
        printNumber(3);
        System.out.println("-----------------");
        printNumber(10);
    }

    public static void printNumber(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(i);
        }
    }

}
