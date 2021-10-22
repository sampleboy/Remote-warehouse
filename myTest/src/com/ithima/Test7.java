package com.ithima;

import java.util.Scanner;

public class Test7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个4位数");
        int number = sc.nextInt();


        int[] arr = new int[4];
        arr[0] = number / 10 / 10 / 10 % 10;
        arr[1] = number / 10 / 10 % 10;
        arr[2] = number / 10 % 10;
        arr[3] = number % 10;
        for (int x = 0; x < arr.length; x++) {
            arr[x] += 5;
            arr[x] %= 10;

        }
        int temp = arr[0];
        arr[0] = arr[3];
        arr[3] = temp;


        temp = arr[1];
        arr[1] = arr[2];
        arr[2] = temp;


        for (int x = 0; x < arr.length; x++) {
            System.out.print(arr[x]);
        }
        System.out.println();
    }

}
