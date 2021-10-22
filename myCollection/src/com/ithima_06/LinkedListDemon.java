package com.ithima_06;

import java.util.LinkedList;

public class LinkedListDemon {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add("hello");
        list.add("world");


        list.addFirst("java");
        list.addLast("android");


        System.out.println(list);
    }
}
