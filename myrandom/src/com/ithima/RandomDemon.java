package com.ithima;

import java.util.Random;

public class RandomDemon {
    public static void main(String[] args) {
        Random r = new Random();
        for (int x = 1; x <= 10; x++) {
            int i = r.nextInt(10);
            System.out.println("i:" + i);
        }
        System.out.println("----------------------");
        int a = r.nextInt(100) + 1;
        System.out.println("a:" + a);
    }
}



