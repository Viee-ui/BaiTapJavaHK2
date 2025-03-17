package IOStreamAndThread;

import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadFileReader {
    static class ReaderThread implements Runnable {
        private String fileName;
        private long start;
        private long length;

        public ReaderThread(String fileName, long start, long length) {
            this.fileName = fileName;
            this.start = start;
            this.length = length;
        }

        @Override
        public void run() {
            try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
                raf.seek(start);
                byte[] buffer = new byte[(int) length];
                raf.read(buffer);
                System.out.println(Thread.currentThread().getName() + ": " + new String(buffer).trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String fileName = "D:/File/largefile.txt";
        int numThreads = 4;  // Có thể tùy chỉnh số lượng thread
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        long fileLength = raf.length();
        long partSize = fileLength / numThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            long start = i * partSize;
            long length = (i == numThreads - 1) ? (fileLength - start) : partSize;
            executor.execute(new ReaderThread(fileName, start, length));
        }

        executor.shutdown();
    }
}
