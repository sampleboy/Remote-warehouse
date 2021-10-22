package com.ithima_07;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class StringTest2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String s = sc.nextLine();

        String result = reverse(s);
        System.out.println("result:" + result);

    }


    /*public static String reverse(String s){
        String ss="";
        for (int x=s.length()-1;x>=0;x--){
            ss+=s.charAt(x);
        }
        return ss;
    }

     */

    public static String reverse(String s) {
        char[] chs = s.toCharArray();


        for (int start = 0, end = s.length() - 1; start < end; start++, end--) {
            char temp = chs[start];
            chs[start] = chs[end];
            chs[end] = temp;
        }
        String ss = new String(chs);
        return ss;
    }
}
