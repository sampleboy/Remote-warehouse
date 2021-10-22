package com.ithima_02;

import java.util.*;

public class IteratorDemon3 {
    public static void main(String[] args) {
        //method();

        List c = new ArrayList();
        //Collection c=new ArrayList();
        c.add("hello");
        c.add("world");
        c.add("java");

        ListIterator lis = c.listIterator();
        while (lis.hasNext()) {
            String s = (String) lis.next();
            if (s.equals("java")) {
                lis.add("android");
            }
        }

        System.out.println(c);

        /*Iterator it=c.iterator();
        while (it.hasNext()){
            String s=(String) it.next();

            if (s.equals("java")){
                c.add("android");
            }*/


    }

    private static void method() {
        Collection c = new ArrayList();
        c.add("hello");
        c.add("world");
        c.add("java");


        if (c.contains("java")) {
            c.add("android");
        }
        System.out.println(c);
    }
}
