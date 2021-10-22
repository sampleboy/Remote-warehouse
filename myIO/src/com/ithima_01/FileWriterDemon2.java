package com.ithima_01;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemon2 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("a.txt");
        fw.write("你好北京");
        fw.flush();

        fw.close();


    }
}
