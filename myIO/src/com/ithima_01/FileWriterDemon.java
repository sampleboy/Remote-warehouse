package com.ithima_01;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemon {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("e:\\a.txt");
        fw.write("IO流你好");
        fw.flush();
        //释放资源；
        fw.close();


    }
}
