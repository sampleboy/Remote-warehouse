package com.ithima_04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class ForEachDemon {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<String>();
        c.add("hello");
        c.add("world");
        c.add("java");
        for (String str : c) {
            System.out.println(str.toUpperCase(Locale.ROOT));
        }
/*        for (Object obj:c){
            System.out.println(obj);

       }*/


    }
}
