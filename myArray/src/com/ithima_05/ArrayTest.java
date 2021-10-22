package com.ithima_05;

public class ArrayTest {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3,}, {4, 5, 6}, {7, 8, 9,}, {10, 11, 12, 13}};


        /*for (int x=0;x<arr[0].length;x++){
            System.out.println(arr[0][x]);
        }
        System.out.println("------------------");
        for (int x=0;x<arr[1].length;x++){
            System.out.println(arr[1][x]);
        }
        System.out.println("------------------");
        for (int x=0;x<arr[2].length;x++){
            System.out.println(arr[2][x]);
        }
        System.out.println("------------------");
        for (int x=0;x<arr[3].length;x++){
            System.out.println(arr[3][x]);
        }

         */
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length; x++) {
                System.out.println(arr[y][x]);
            }
        }
        System.out.println("-------------------");
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length; x++) {
                System.out.print(arr[y][x]);
            }
            System.out.println();
        }
    }
}
