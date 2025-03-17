package IOStreamAndThread;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class FileWriterTask implements Runnable {
    private static final Lock lock = new ReentrantLock();
    private final String fileName;
    private final String content;

    public FileWriterTask(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    @Override
    public void run() {
        lock.lock();
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content + "\n");
            System.out.println(Thread.currentThread().getName() + " đã ghi: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MultiThreadFileWrite {
    public static void main(String[] args) {
        String fileName = "D:/output.txt";

        Thread thread1 = new Thread(new FileWriterTask(fileName, "Hello from Thread 1"));
        Thread thread2 = new Thread(new FileWriterTask(fileName, "Hello from Thread 2"));

        thread1.start();
        thread2.start();
    }
}