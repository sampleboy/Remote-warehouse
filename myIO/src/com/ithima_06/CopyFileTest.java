package com.ithima_06;

import com.sun.org.apache.xalan.internal.res.XSLTErrorResources_zh_CN;

import java.io.*;

public class CopyFileTest {
    public static void main(String[] args) throws IOException {
        String srcFileName = "FileWriterDemon.java";
        String desFileName = "Copy.java";

        method1(srcFileName, desFileName);
        method2(srcFileName, desFileName);
        method3(srcFileName, desFileName);
        method4(srcFileName, desFileName);
        method5(srcFileName, desFileName);
    }

    public static void method5(String srcFileName, String desFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(srcFileName));
        BufferedWriter bw = new BufferedWriter(new FileWriter(desFileName));
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();
    }

    public static void method4(String srcFileName, String desFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(srcFileName));
        BufferedWriter bw = new BufferedWriter(new FileWriter(desFileName));

        char[] chs = new char[1024];
        int len;
        while ((len = br.read(chs)) != -1) {
            bw.write(chs);
        }
        br.close();
        bw.close();
    }

    public static void method3(String srcFileName, String desFileName) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(srcFileName));
        BufferedWriter bw = new BufferedWriter(new FileWriter(desFileName));

        int ch;
        while ((ch = br.read()) != -1) {
            bw.write(ch);
        }
        bw.close();
        br.close();

    }


    public static void method2(String srcFileName, String desFileName) throws IOException {
        FileReader fr = new FileReader(srcFileName);
        FileWriter fw = new FileWriter(desFileName);

        char[] chs = new char[1024];
        int len;
        while ((len = fr.read(chs)) != -1) {
            fw.write(chs, 0, len);
        }
        fr.close();
        fw.close();
    }

    public static void method1(String srcFileName, String desFileName) throws IOException {
        FileReader fr = new FileReader(srcFileName);
        FileWriter fw = new FileWriter(desFileName);

        int ch;
        while ((ch = fr.read()) != -1) {
            fw.write(ch);
        }
        fw.close();
        fr.close();
    }


}
