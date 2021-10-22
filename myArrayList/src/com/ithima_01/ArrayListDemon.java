package com.ithima_01;

import java.util.ArrayList;

public class ArrayListDemon {
    public static void main(String[] args) {

        ArrayList<String> array = new ArrayList<>();
        array.add("hello");
        array.add("world");
        array.add(1, "android");


        System.out.println("array:" + array);
    }
}
