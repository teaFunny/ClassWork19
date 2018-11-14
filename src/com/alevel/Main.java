package com.alevel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        doAll();
    }

    public static void doAll() {
        try (FileWriter writer = new FileWriter("test.txt")) {
            File directory = new File(getDirectoryPath());
            if (directory.exists() && directory.isDirectory() && directory.listFiles() != null) {
                writeAllFiles(writer, directory, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAllFiles(FileWriter writer, File directory, int enters) throws IOException {
        for (File file : directory.listFiles()) {
            writer.write(generateBlankString(enters) + file.getName() + ":" + file.lastModified() + "\r\n");
            if (file.isDirectory() && file.listFiles() != null) {
                writeAllFiles(writer, file, ++enters);
            }
        }
    }

    private static String getDirectoryPath() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.next();
        }
    }

    private static String generateBlankString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += " ";
        }
        return result;
    }
}