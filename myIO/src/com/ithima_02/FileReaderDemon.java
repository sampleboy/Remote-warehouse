package com.ithima_02;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemon {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("fr.txt");
        /*int ch=fr.read();
        System.out.println(ch);
        System.out.println((char)ch);


        ch=fr.read();
        System.out.println(ch);
        System.out.println((char)ch);

        ch=fr.read();
        System.out.println(ch);
        System.out.println((char)ch);


         */
        int ch;
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }
        fr.close();
    }
}
