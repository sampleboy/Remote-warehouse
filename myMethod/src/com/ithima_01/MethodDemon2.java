package com.ithima_01;

public class MethodDemon2 {
    public static void main(String[] args) {
        //输出调用
        //System.out.println(sum(10,20));
        int result = sum(10, 20);
        //result +=10;
        System.out.println(result);
    }

    public static int sum(int a, int b) {
        int c = a + b;
        return c;
    }
}
