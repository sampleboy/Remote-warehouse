package com.ithima_01;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemon3 {
    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter("b.txt");
        // fw.write("beijingnihao");
        //fw.write("abcde",0,5);
        //fw.write("abcde",1,3);
        //fw.write('a');
        //fw.write(97);
        char[] chs = {'a', 'b', 'c', 'd', 'f'};
        fw.write(chs, 2, 3);

        fw.close();


    }
}
