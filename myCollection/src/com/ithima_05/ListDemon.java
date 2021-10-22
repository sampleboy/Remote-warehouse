package com.ithima_05;

import java.util.ArrayList;
import java.util.List;

public class ListDemon {
    public static void main(String[] args) {
        List list = new ArrayList();


        list.add(0, "hello");
        list.add(0, "world");
        list.add(1, "java");

 /*       for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
*/
        System.out.println(list.remove(1));
        System.out.println(list);
    }
}
