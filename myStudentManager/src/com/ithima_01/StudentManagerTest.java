package com.ithima_01;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagerTest {
    public static void main(String[] args) {
        ArrayList<Student> array = new ArrayList<Student>();

        while (true) {
            System.out.println("——————————欢迎来到学生管理系统————————");
            System.out.println("1查看所有学生");
            System.out.println("2添加学生");
            System.out.println("3删除学生");
            System.out.println("4修改学生");
            System.out.println("5退出");
            System.out.println("请输入你的选择：");
            Scanner sc = new Scanner(System.in);
            String choiceString = sc.nextLine();
            switch (choiceString) {
                case "1":
                    findAllStudent(array);
                    break;
                case "2":
                    addStudent(array);
                    break;
                case "3":
                    deleteStudent(array);
                    break;
                case "4":
                    updateStudent(array);
                    break;
                case "5":
                default:
                    System.out.println("谢谢你的使用！");
                    System.exit(0);
                    break;
            }

        }
    }

    //查看所有学生；
    public static void findAllStudent(ArrayList<Student> array) {
        if (array.size() == 0) {
            System.out.println("不好意思，目前没有学生信息可供查询，请回去重新选择你的操作");
            return;
        }
        System.out.println("学号\t\t姓名\t年龄\t居住地");
        for (int x = 0; x < array.size(); x++) {
            Student s = array.get(x);
            System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getAge() + "\t" + s.getAddress());
        }
    }

    public static void addStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        String id;
        while (true) {
            System.out.println("请输入学生学号：");
            id = sc.nextLine();


            boolean flag = false;
            for (int x = 0; x < array.size(); x++) {
                Student s = array.get(x);
                if (s.getId().equals(id)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("你输入的学号被占用，请从新输入");
            } else {
                break;
            }
        }
        System.out.println("请输入学生姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生年龄：");
        String age = sc.nextLine();
        System.out.println("请输入学生居住地：");
        String address = sc.nextLine();
        //创建学生对象
        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);
        array.add(s);
        System.out.println("添加学生成功！");

    }


    public static void deleteStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要删除的学生的学号：");
        String id = sc.nextLine();
        int index = -1;
        for (int x = 0; x < array.size(); x++) {
            Student s = array.get(x);
            if (s.getId().equals(id)) {
                index = x;
                break;
            }
            if (index == -1) {
                System.out.println("不好意思，你要删除的学号对应的学生信息不存在，请回去重新你的选择");
            } else {
                array.remove(index);
                System.out.println("删除学生成功！");
            }
        }
        System.out.println("删除学生成功！");
    }

    public static void updateStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要修改的学号：");
        String id = sc.nextLine();

        int index = -1;
        for (int x = 0; x < array.size(); x++) {
            Student s = array.get(x);

            if (s.getId().equals(id)) {
                index = x;
                break;
            }
        }

        if (index == -1) {
            System.out.println("不好意思，你要修改的学号不存在，请回去重新选择");
        } else {
            System.out.println("请输入学生新姓名：");
            String name = sc.nextLine();
            System.out.println("请输入学生心年龄：");
            String age = sc.nextLine();
            System.out.println("请输入学生新地址");
            String address = sc.nextLine();

            Student s = new Student();
            s.setId(id);
            s.setName(name);
            s.setAge(age);
            s.setAddress(address);

            array.set(index, s);
            System.out.println("修改学生成功！");


        }


    }


}
