package com.ithima_03;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFileDemon2 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("fr2.txt");
        FileWriter fw = new FileWriter("Copy.txt");


        char[] chs = new char[1024];
        int len;
        while ((len = fr.read(chs)) != -1) {
            fw.write(chs, 0, len);
        }
        fr.close();
        fw.close();
    }
}
