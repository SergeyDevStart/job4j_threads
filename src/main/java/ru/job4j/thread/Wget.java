package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        check(url, speed);
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        File file = new File("tmp.xml");
        byte[] buffer = new byte[1024];
        try (InputStream input = new URL(url).openStream();
             FileOutputStream out = new FileOutputStream(file)) {
            int downloadByte;
            while ((downloadByte = input.read(buffer, 0, buffer.length)) != -1) {
                long startTimeStamp = System.nanoTime();
                out.write(buffer, 0, downloadByte);
                double downloadTime = System.nanoTime() - startTimeStamp;
                double totalSpeed = buffer.length / downloadTime * 1000000;
                if (speed < totalSpeed) {
                    try {
                        Thread.sleep((long) totalSpeed / speed);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void check(String url, int speed) {
        try {
            new URL(url).toURI();
        } catch (Exception e) {
            throw new IllegalStateException("The URL " + url + " isn't valid.");
        }
        if (speed <= 0) {
            throw new IllegalStateException("This value: " + speed + " is incorrect");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
