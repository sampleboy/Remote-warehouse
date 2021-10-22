package com.ithima_01;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetDemon {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();

        set.add("hello");
        set.add("world");
        set.add("java");
//        转数组
//        method(set);
//        method2(set);
//        method3(set);
        System.out.println(set.add("java"));
    }

    private static void method3(Set<String> set) {
        //        增强for
        for (String s : set) {
            System.out.println(s);
        }
    }

    private static void method2(Set<String> set) {
        //    迭代器
        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
        }
    }

    private static void method(Set<String> set) {

        Object[] objs = set.toArray();
        for (int x = 0; x < objs.length; x++) {
            System.out.println(objs[x]);
        }
    }
}
