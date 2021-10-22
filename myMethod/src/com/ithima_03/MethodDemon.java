package com.ithima_03;

public class MethodDemon {
    public static void main(String[] args) {


        int result = sum(10, 15);
        System.out.println("result:" + result);

        System.out.println("-------------");
        int result2 = sum(10, 35, 13);
        System.out.println("result2:" + result2);
    }


    public static int sum(int a, int b, int c) {
        return a + b + c;
    }


    public static int sum(int a, int b) {
        return a + b;
    }
}

