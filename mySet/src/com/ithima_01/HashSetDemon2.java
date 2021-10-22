package com.ithima_01;

import javax.rmi.CORBA.StubDelegate;
import java.util.HashSet;

public class HashSetDemon2 {
    public static void main(String[] args) {
//         创建集合对象
        HashSet<Student> hs = new HashSet<Student>();

//         创建元素对象
        Student s1 = new Student("zhangsan", 19);
        Student s2 = new Student("lisi", 20);
        Student s3 = new Student("lisi", 20);
//
        hs.add(s1);
        hs.add(s2);
        hs.add(s3);

//        遍历集合对象
        for (Student student : hs) {
            System.out.println(student);
        }
    }

    static class Student {
        String name;
        int age;

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }


        @Override
        public boolean equals(Object obj) {
            Student s = (Student) obj;
            if (this.age != s.age) {
                return false;
            }
            if (this.name.equals(s.name)) {
                return false;
            }
            return true;
        }
    }
}
