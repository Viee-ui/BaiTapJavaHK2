package IOStreamAndThread;

import java.io.*;
import java.net.*;

public class URLDownloaderThread {
    public static void main(String[] args) {
        String url = "https://baomoi.com/";  // URL cần tải
        String outputFile = "D:/File/content.txt";

        Thread downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFromURL(url, outputFile);
            }
        });

        downloadThread.start();

        try {
            downloadThread.join();
            System.out.println("Tải dữ liệu hoàn tất!");
        } catch (InterruptedException e) {
            System.out.println("Thread bị gián đoạn: " + e.getMessage());
        }
    }

    private static void downloadFromURL(String urlString, String outputFile) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(
                         new FileWriter(outputFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            System.out.println("URL không hợp lệ: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }
}
