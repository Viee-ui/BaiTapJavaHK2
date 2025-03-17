package IOStreamAndThread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FileReaderThread extends Thread {
    private String fileName;

    public FileReaderThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(Thread.currentThread().getName() + ": " + line);
                Thread.sleep(500);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
}

class FileReaderWithThread {
    public static void main(String[] args) {
        String fileName = "D:/Kiều.txt";
        FileReaderThread thread = new FileReaderThread(fileName);
        thread.start();
    }
}