package ru.job4j.io;

import java.io.*;

public class ContentWriter {
    private final File file;

    public ContentWriter(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (var output = new BufferedOutputStream(new FileOutputStream(file))) {
            output.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
