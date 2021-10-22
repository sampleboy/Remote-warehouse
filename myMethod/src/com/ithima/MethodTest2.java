package com.ithima;

public class MethodTest2 {
    public static void main(String[] args) {
        int[] arr = {32, 49, 05, 36};

        /*int max=arr[0];
        for (int x=1;x<arr.length;x++){
            if (arr[x]>max){
                max=arr[x];
            }
        }  */
        int max = getMax(arr);

        System.out.println("max:" + max);
    }


    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > max) {
                max = arr[x];
            }
        }
        return max;
    }
}
