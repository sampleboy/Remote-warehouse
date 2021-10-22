package com.ithima_01;

import java.util.ArrayList;

public class ArrayListDemon2 {
    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();

        array.add("hello");
        array.add("world");
        array.add("java");


        //System.out.println("array:"+array.get(0));
        //System.out.println("size:"+array.size());
        //System.out.println("remove:"+array.remove("java"));

        System.out.println("set:" + array.set(1, "android"));
        //System.out.println("remove:"+array.remove(0));
        System.out.println("array:" + array);

    }
}
