package com.ithima_07;

public class ContinueDemon {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                continue;
            }
            System.out.println("HelloWorld");
        }
    }
}
