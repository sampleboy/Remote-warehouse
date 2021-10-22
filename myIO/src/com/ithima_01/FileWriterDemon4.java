package com.ithima_01;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemon4 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("c.txt", true);

        for (int x = 0; x < 10; x++) {
            fw.write("hello" + x);
            fw.write("\r\n");
        }
        fw.close();
    }
}
