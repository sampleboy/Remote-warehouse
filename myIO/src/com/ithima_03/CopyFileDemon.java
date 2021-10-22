package com.ithima_03;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFileDemon {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("FileWriterDemon.java");
        FileWriter fw = new FileWriter("Copy.java");


        int ch;
        while ((ch = fr.read()) != -1) {
            fw.write(ch);
        }
        fw.close();
        fr.close();
    }
}
