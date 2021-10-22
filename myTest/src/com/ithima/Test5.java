package com.ithima;

import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        int[] arr = new int[6];
        //创建键盘录入对象
        Scanner sc = new Scanner(System.in);
        //键盘录入分数
        for (int x = 0; x < arr.length; x++) {
            System.out.println("请输入第" + (x + 1) + "个评委给出的分数（0-100）：");
            int score = sc.nextInt();
            arr[x] = score;
        }
        int max = getMax(arr);
        int min = getMin(arr);
        int sum = sum(arr);
        int avg = (sum - max - min) / arr.length - 2;
        System.out.println("该选手的最终得分是：" + avg);

    }

    public static int sum(int[] arr) {
        int sum = 0;
        for (int x = 0; x < arr.length; x++) {
            sum += arr[x];
        }
        return sum;
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


    public static int getMin(int[] arr) {
        int min = arr[0];
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] < min) {
                min = arr[x];
            }
        }
        return min;
    }
}
