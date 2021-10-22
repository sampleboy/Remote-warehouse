package com.ithima_04;

import java.io.*;

public class BufferedStreamDemon {
    public static void main(String[] args) throws IOException {
        /*BufferedWriter bw=new BufferedWriter(new FileWriter("bw.txt"));
        bw.write("hello");
        bw.flush();
        bw.close();


        BufferedReader br=new BufferedReader(new FileReader("fr2.txt"));

        int ch;
        while((ch=br.read())!=-1){
            System.out.print((char)ch);
        }
        br.close();

         */
        BufferedReader br = new BufferedReader(new FileReader("fr2.txt"));


        char[] chs = new char[1024];
        int len;
        while ((len = br.read(chs)) != -1) {
            System.out.print(new String(chs, 0, len));
        }
        br.close();
    }


}
