package com.ithima;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        //键盘录入数据，用scanner实现；
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入月份（1-12）：");
        //接收数据；
        int month = sc.nextInt();
    /*
        if (month>12 || month<1){
            System.out.println("你输入的月份有误；");
        }else if (month == 1) {
            System.out.println("冬季");
        }else if (month==2){
            System.out.println("冬季");
        }else if (month==3){
            System.out.println("春季");
        }else if (month==4){
            System.out.println("春季");
        }else if (month==5){
            System.out.println("春季");
        }else if (month==6){
            System.out.println("夏季");
        }else if (month==7){
            System.out.println("夏季");
        }else if (month==8){
            System.out.println("夏季");
        }else if (month==9){
            System.out.println("秋季");
        }else if (month==10){
            System.out.println("秋季");
        }else if (month==11){
            System.out.println("秋季");
        }else if (month==12){
            System.out.println("冬季");
        }

     */
       /* if ((month==1) ||(month==2) ||(month==12)){
            System.out.println("冬季");
        }else if ((month==3) ||(month==4) ||(month==5)){
            System.out.println("春季");
        }else if ((month==6) ||(month==7) ||(month==8)){
            System.out.println("夏季");
        }else if ((month==9) ||(month==10) ||(month==11)){
            System.out.println("秋季");
        }else if(month>12 ||month<1){
            System.out.println("你输入的月份有误；");
        }

        */
/*
        switch (month){
            case 1:
                System.out.println("冬季");
                break;
            case 2:
                System.out.println("冬季");
                break;
            case 3:
                System.out.println("春季");
                break;
            case 4:
                System.out.println("春季");
                break;
            case 5:
                System.out.println("春季");
                break;
            case 6:
                System.out.println("夏季");
                break;
            case 7:
                System.out.println("夏季");
                break;
            case 8:
                System.out.println("夏季");
                break;
            case 9:
                System.out.println("秋季");
                break;
            case 10:
                System.out.println("秋季");
                break;
            case 11:
                System.out.println("秋季");
                break;
            case 12:
                System.out.println("冬季");
                break;
            default:
                System.out.println("你输入的月份有误；");

 */
        //case 穿透;
/*        switch (month){
            case 1:
                System.out.println("冬季");
                break;
            case 2:
                System.out.println("冬季");
                break;
            case 3:
                System.out.println("春季");
                break;

 */
        switch (month) {
            case 1:
            case 2:
            case 12:
                System.out.println("冬季");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("春季");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("夏季");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("冬季");
                break;
            default:
                System.out.println("你输入的月份有误");
                break;
        }
    }

}
