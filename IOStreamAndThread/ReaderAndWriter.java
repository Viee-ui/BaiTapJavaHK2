package IOStreamAndThread;

import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ReaderAndWriter {
    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    static class Reader extends Thread {
        private String source;

        public Reader(String source) {
            this.source = source;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new FileReader(source))) {
                String line;
                while ((line = br.readLine()) != null) {
                    queue.put(line);
                }
                queue.put("END"); // Báo hiệu kết thúc
            } catch (Exception e) {
                e.printStackTrace();
                queue.offer("END"); // Đảm bảo Writer không bị treo nếu Reader gặp lỗi
            }
        }
    }

    static class Writer extends Thread {
        private String destination;

        public Writer(String destination) {
            this.destination = destination;
        }

        @Override
        public void run() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(destination))) {
                while (true) {
                    String line = queue.take();
                    if ("END".equals(line)) break; // Kiểm tra chuỗi kết thúc
                    bw.write(line);
                    bw.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String sourceFile = "D:/File/source.txt";
        String destinationFile = "D:/File/destination.txt";

        Reader reader = new Reader(sourceFile);
        Writer writer = new Writer(destinationFile);

        reader.start();
        writer.start();

        try {
            reader.join(); // Chờ reader kết thúc
            writer.join(); // Chờ writer kết thúc
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Copy hoàn tất!");
    }
}
