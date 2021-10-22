package com.ithima;

import org.jetbrains.annotations.NotNull;

public class MethodTest {
    public static void main(String[] args) {

        int[] arr = {11, 22, 33, 44, 55};
        /*for (int x=0;x<arr.length;x++){
            System.out.println(arr[x]);
        }
         */
        // printArry(arr);
        printArry(arr);
    }

    /*public static void printArry(int[] arr){
        for (int x=0;x<arr.length;x++){
            System.out.println(arr[x]);
        }
            }
     */
    public static void printArry(int @NotNull [] arr) {
        System.out.print("[");
        for (int x = 0; x < arr.length; x++) {
            if (x == arr.length - 1) {
                System.out.println(arr[x] + "]");
            } else {
                System.out.print(arr[x] + ",");
            }

        }
    }
}
