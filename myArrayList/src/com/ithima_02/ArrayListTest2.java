package com.ithima_02;

import java.util.ArrayList;

public class ArrayListTest2 {
    public static void main(String[] args) {
        String[] strArray = {"张三丰", "宋远桥", "英梨亭", "张翠山", "莫声谷"};

        ArrayList<String> array = new ArrayList<>();

        for (int x = 0; x < strArray.length; x++) {
            array.add(strArray[x]);

        }


        for (int x = 0; x < array.size(); x++) {
            String s = array.get(x);

            if (s.startsWith("张")) {
                System.out.println(s);
            }

        }
    }
}
