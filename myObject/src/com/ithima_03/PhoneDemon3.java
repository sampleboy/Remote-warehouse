package com.ithima_03;

public class PhoneDemon3 {
    public static void main(String[] args) {
        Phone p = new Phone();
        p.brend = "oppo";
        p.price = 2900;
        p.color = "黄三色";
        System.out.println(p.brend + "-----" + p.price + "--------" + p.color);


        Phone p2 = p;
        p2.brend = "美滋";
        p2.price = 39900;
        p2.color = "白色";
        System.out.println(p2.brend + "-----" + p2.price + "--------" + p2.color);
    }
}
