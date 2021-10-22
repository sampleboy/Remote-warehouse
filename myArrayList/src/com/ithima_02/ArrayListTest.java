package com.ithima_02;

import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {


        ArrayList<String> array = new ArrayList<>();


        array.add("猪八戒");
        array.add("孙悟空");
        array.add("沙和尚");
        array.add("唐三藏");

        for (int x = 0; x < array.size(); x++) {
            String s = array.get(x);
            System.out.println(s);
        }
    }
}
