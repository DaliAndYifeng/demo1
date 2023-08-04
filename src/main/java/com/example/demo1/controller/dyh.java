package com.example.demo1.controller;

import jdk.internal.util.xml.impl.ReaderUTF8;

import java.io.*;

public class dyh {

    public static void main(String[] args) throws IOException {

        File text = new File("D:/develop/IdeaProjects/gap_new/crm/demo1/target/classes/dyh/1.text");
        FileInputStream fileInputStream = new FileInputStream(text);
        Reader reader = new ReaderUTF8(fileInputStream);
        int read = reader.read();
        System.out.println(read);
    }
}
