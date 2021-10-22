package com.ithima_05;


public class StringDemon {
    public static void main(String[] args) {
        String s = "abcdef";

        char[] chs = s.toCharArray();
        for (int x = 0; x < chs.length; x++) {
            System.out.println(chs[x]);
        }
    }

}
