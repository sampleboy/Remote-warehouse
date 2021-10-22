package com.ithima_07;

import java.util.SplittableRandom;

public class StringTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};


        String s = arrayToString(arr);
        System.out.println("s:" + s);
    }

    public static String arrayToString(int[] arr) {
        String s = "";
        s += "[";
        for (int x = 0; x < arr.length; x++) {
            if (x == arr.length - 1) {
                s += arr[x];
            } else {
                s += arr[x];
                s += ",";
            }
        }
        s += "]";
        return s;
    }
}
