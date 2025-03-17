package IOStreamAndThread;

import java.io.*;
import java.util.Scanner;

class FileWriterThread extends Thread {
    private String fileName;

    public FileWriterThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            System.out.println("Nhập dữ liệu (gõ 'exit' để thoát):");
            while (true) {
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Đã thoát và lưu dữ liệu vào file.");
                    break;
                }
                writer.write(input);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("Lỗi ghi file: " + e.getMessage());
        }
    }
}

class FileWriterApp {
    public static void main(String[] args) {
        FileWriterThread writerThread = new FileWriterThread("D:/File/B6.txt");
        writerThread.start();
    }
}
