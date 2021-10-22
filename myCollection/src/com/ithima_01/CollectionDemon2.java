package com.ithima_01;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemon2 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        System.out.println(c.add("hello"));
        System.out.println(c.add("world"));

        System.out.println(c.contains("hello"));
        System.out.println(c);
    }
}
