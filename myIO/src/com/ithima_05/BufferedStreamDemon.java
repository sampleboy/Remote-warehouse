package com.ithima_05;

import java.io.*;

public class BufferedStreamDemon {
    public static void main(String[] args) throws IOException {
/*        BufferedWriter bw=new BufferedWriter(new FileWriter("bw2.txt"));
        for (int x=0;x<10;x++){
            bw.write("hello"+x);
            bw.newLine();
            bw.flush();
        }
        bw.close();

 */
        BufferedReader br = new BufferedReader(new FileReader("br.txt"));
/*
        String line=br.readLine();
        System.out.println(line);


        line=br.readLine();
        System.out.println(line);

 */
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }


}
