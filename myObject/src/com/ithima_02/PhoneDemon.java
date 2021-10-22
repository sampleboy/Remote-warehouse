package com.ithima_02;

public class PhoneDemon {
    public static void main(String[] args) {
        Phone p = new Phone();
        System.out.println("品牌" + p.brend);
        System.out.println("价格：" + p.price);
        System.out.println("颜色：" + p.color);
        System.out.println("------");


        p.brend = "三星";
        p.price = 29000;
        p.color = "白色";

        System.out.println("品牌" + p.brend);
        System.out.println("价格：" + p.price);
        System.out.println("颜色：" + p.color);
        System.out.println("------");

        p.call("lingqinxing");
        p.sendMessage();
    }
}
