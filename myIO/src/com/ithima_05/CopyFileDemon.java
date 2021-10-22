package com.ithima_05;

import java.io.*;
import java.nio.Buffer;

public class CopyFileDemon {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("fr2.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("Copy.java"));

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();

        }
        bw.close();
        br.close();


    }
}
