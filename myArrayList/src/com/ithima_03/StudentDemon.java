package com.ithima_03;

import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDemon {
    public static void main(String[] args) {
        ArrayList<Student> array = new ArrayList<Student>();
/*
        ArrayList<Student> array=new ArrayList<Student>();
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入学生姓名：");
        String name=sc.nextLine();
        System.out.println("请输入学生年龄：");
        String age=sc.nextLine();

        Student s=new Student();
        s.getName();
        s.getAge();

        array.add(s);
 */
        addStudent(array);
        addStudent(array);
        addStudent(array);
        for (int x = 0; x < array.size(); x++) {
            Student s = array.get(x);
            System.out.println(s.getName() + "---------" + s.getAge());
        }

    }

    public static void addStudent(ArrayList<Student> array) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生年龄：");
        String age = sc.nextLine();

        Student s = new Student();
        s.setName(name);
        s.setAge(age);

        array.add(s);
    }


}
