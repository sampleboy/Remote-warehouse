package com.ithima_04;

public class Arraytest {
    public static void main(String[] args) {

        int[] arr = {12, 45, 98, 73, 60};


        int max = arr[0];
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > max) {
                max = arr[x];
            }
        }
        System.out.println("max:" + max);

    }
}
