package com.ithima;

import java.util.Scanner;

public class Test6 {
    public static void main(String[] args) {
        int[] arr = new int[5];
        //创建键盘录入数据
        Scanner sc = new Scanner(System.in);
        for (int x = 0; x < arr.length; x++) {
            System.out.println("请输入" + (x + 1) + "个元素：");
            //接收数据
            int number = sc.nextInt();
            arr[x] = number;
        }
        printArry(arr);

        reverse(arr);
        printArry(arr);

    }

    public static void printArry(int[] arr) {
        System.out.print("[");
        for (int x = 0; x < arr.length; x++) {
            if (x == arr.length - 1) {
                System.out.println(arr[x] + "]");
            } else {
                System.out.print(arr[x] + ",");
            }
        }
    }


    public static void reverse(int[] arr) {
        for (int start = 0, end = arr.length - 1; start <= end; start++, end--) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
    }
}
