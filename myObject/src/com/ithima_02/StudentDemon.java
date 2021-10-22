package com.ithima_02;

public class StudentDemon {
    public static void main(String[] args) {
        Student s = new Student();


        System.out.println("姓名" + s.name);
        System.out.println("年龄" + s.age);


        System.out.println("-----------");


        s.name = "林青霞";
        s.age = 28;
        System.out.println("姓名：" + s.name);
        System.out.println("年龄：" + s.age);

        System.out.println("-----------");


        s.study();
        s.eat();


    }
}
