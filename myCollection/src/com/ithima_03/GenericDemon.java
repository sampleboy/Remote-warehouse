package com.ithima_03;

import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GenericDemon {
    public static void main(String[] args) {
        Collection<Student> c = new ArrayList<Student>();
        Student s = new Student("张三", 10);
        Student s2 = new Student("李四", 23);

        c.add(s);
        c.add(s2);
        Iterator<Student> it = c.iterator();
        while (it.hasNext()) {
            Student stu = it.next();
            System.out.println(stu.name);
        }


       /* Collection<Student> c = new ArrayList<Student>();

        Student s = new Student("zhangsan", 18);
        Student s2 = new Student("lisi", 30);
        c.add(s);
        c.add(s2);
        Iterator<Student> it = c.iterator();
        while (it.hasNext()) {
//            String str = (String) it.next();
//            System.out.println(str);
            Student stu=it.next();
            System.out.println(stu.name);
        }*/


    }
}


class Student {
    String name;
    int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
   /* class Student{
        String name;
        int age;



        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
    */

