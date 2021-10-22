package com.ithima_04;

public class ArratTest {
    public static void main(String[] args) {
        int[] arr = {11, 12, 13, 14, 15};


        System.out.println(arr);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr[3]);
        System.out.println(arr[4]);


        System.out.println("-------------");
        for (int x = 0; x <= 4; x++) {
            System.out.println(arr[x]);
        }


        System.out.println("-------------");
        System.out.println("数组共有" + arr.length + "个");
        for (int x = 0; x <= arr.length; x++) {
            System.out.println(arr[x]);

        }

    }
}
