package IOStreamAndThread;

import java.io.*;

public class CharacterCountThread {
    public static void main(String[] args) {
        String sourceFile = "D:/File/sources.txt";
        String resultFile = "D:/File/result.txt";

        Thread countThread = new Thread(new Runnable() {
            @Override
            public void run() {
                countCharacters(sourceFile, resultFile);
            }
        });

        countThread.start();

        try {
            countThread.join();
            System.out.println("Đã hoàn thành đếm ký tự!");
        } catch (InterruptedException e) {
            System.out.println("Thread bị gián đoạn: " + e.getMessage());
        }
    }

    private static void countCharacters(String sourceFile, String resultFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {

            int totalChars = 0;
            int currentChar;

            // Đếm từng ký tự trong file
            while ((currentChar = reader.read()) != -1) {
                totalChars++;
            }

            // Ghi kết quả vào file
            writer.write("Tổng số ký tự trong file: " + totalChars);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Lỗi xử lý file: " + e.getMessage());
        }
    }
}
