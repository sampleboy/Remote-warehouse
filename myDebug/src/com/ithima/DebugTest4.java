package com.ithima;

public class DebugTest4 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        for (int x = 0; x < arr.length; x++) {
            System.out.println(arr[x]);
        }
        change(arr);
        for (int x = 0; x < arr.length; x++) {
            System.out.println(arr[x]);
        }
    }

    public static void change(int[] arr) {
        for (int x = 0; x < arr.length; x++) {
            if (arr[x] % 2 == 0) {
                arr[x] *= 2;
            }
        }
    }
}
