package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        check(url, speed, fileName);
        File file = new File(fileName);
        byte[] buffer = new byte[1024];
        try (InputStream input = new URL(url).openStream();
             FileOutputStream out = new FileOutputStream(file)) {
            int downloadByte;
            int totalByte = 0;
            long startTimeStamp = System.currentTimeMillis();
            while ((downloadByte = input.read(buffer, 0, buffer.length)) != -1) {
                totalByte += downloadByte;
                if (totalByte > speed) {
                    long difference = System.currentTimeMillis() - startTimeStamp;
                    if (difference < 1000) {
                        try {
                            Thread.sleep(1000 - difference);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    totalByte = 0;
                    startTimeStamp = System.currentTimeMillis();
                }
                out.write(buffer, 0, downloadByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void check(String url, int speed, String fileName) {
        try {
            new URL(url).toURI();
        } catch (Exception e) {
            throw new IllegalStateException("The URL " + url + " isn't valid.");
        }
        if (speed <= 0) {
            throw new IllegalStateException("This value: " + speed + " is incorrect");
        }
        if (!fileName.matches("^[A-Za-z]{1,8}\\.[A-Za-z]{1,3}$")) {
            throw new IllegalStateException("Invalid file name");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalStateException("Arguments not passed to program.");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();
    }
}
