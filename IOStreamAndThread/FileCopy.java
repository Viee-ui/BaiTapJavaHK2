package IOStreamAndThread;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        String sourceFile = "D:/File/source.txt";
        String destFile = "D:/File/dest.txt";

        Thread copyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                copyFile(sourceFile, destFile);
            }
        });

        copyThread.start();

        try {
            copyThread.join();
            System.out.println("Sao chép file hoàn tất!");
        } catch (InterruptedException e) {
            System.out.println("Thread bị gián đoạn: " + e.getMessage());
        }
    }

    private static void copyFile(String sourceFile, String destFile) {
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi sao chép file: " + e.getMessage());
        }
    }
}