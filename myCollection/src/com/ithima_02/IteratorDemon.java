package com.ithima_02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorDemon {
    public static void main(String[] args) {
        //method();

        Collection c = new ArrayList();

        c.add("hello");
        c.add("world");
        c.add("java");

        Iterator it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println(it.next());
    }

    private static void method() {
        Collection c = new ArrayList();


        c.add("hello");
        c.add("world");
        c.add("java");

        Object[] objs = c.toArray();

        for (int x = 0; x < objs.length; x++) {
            System.out.println(objs[x]);
        }
    }
}
