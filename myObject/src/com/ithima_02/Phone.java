package com.ithima_02;

public class Phone {

    String brend;
    int price;
    String color;

    public void call(String name) {

        System.out.println("给" + name + "打电话");
    }

    public void sendMessage() {
        System.out.println("群发短信");
    }

}
