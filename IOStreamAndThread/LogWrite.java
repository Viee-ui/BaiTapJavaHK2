package IOStreamAndThread;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

class LogWriter extends Thread {
    private final String logFile;

    public LogWriter(String logFile) {
        this.logFile = logFile;
    }

    public synchronized void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFile, true))) {
            String logMessage = LocalDateTime.now() + " - " + message;
            out.println(logMessage);
            System.out.println("Log ghi: " + logMessage);
        } catch (IOException e) {
            System.err.println("Lỗi ghi log: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            log("Hệ thống đang chạy...");
            try {
                Thread.sleep(5000); // Ghi log mỗi 5 giây
            } catch (InterruptedException e) {
                System.err.println("Luồng bị gián đoạn");
                break;
            }
        }
    }
}


class LogApp {
    public static void main(String[] args) {
        LogWriter logWriter = new LogWriter("D:/File/B5.txt");
        logWriter.start();

        for (int i = 1; i <= 5; i++) {
            logWriter.log("Sự kiện " + i + " xảy ra.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
