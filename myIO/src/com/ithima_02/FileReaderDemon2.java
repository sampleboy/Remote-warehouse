package com.ithima_02;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemon2 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("fr2.txt");


        char[] chs = new char[5];

        int len = fr.read(chs);
        System.out.println("len:" + len);
        System.out.println(new String(chs));

        len = fr.read(chs);
        System.out.println("len:" + len);
        System.out.println(new String(chs));

        len = fr.read(chs);
        System.out.println("len:" + len);
        System.out.println(new String(chs, 0, len));

    }
}
