package com.ithima_07;

import com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add("hello");
        list.add("world");
        list.add("java");

//        int index=index(list,"java");
//        System.out.println(index);
        boolean flag = contain(list, "java");
        System.out.println(flag);
    }

    public static int index(List list, Object other) {
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj.equals(other)) {
                return i;
            }

        }
        return -1;
    }

    public static boolean contain(List list, Object other) {
        int index = index(list, other);
        if (index >= 0) {
            return true;
        } else {
            return false;
        }
    }


}
