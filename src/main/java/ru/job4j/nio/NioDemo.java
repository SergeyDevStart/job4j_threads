package ru.job4j.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioDemo {
    public static void main(String[] args) {
        int count;
        try (SeekableByteChannel channel = Files.newByteChannel(Path.of("data/nio.txt"))) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            do {
                count = channel.read(buffer);
                if (count != -1) {
                    buffer.flip();
                    for (int i = 0; i < count; i++) {
                        System.out.print((char) buffer.get());
                    }
                }
            } while (count != -1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
