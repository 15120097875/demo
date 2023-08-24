package com.mylock.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @ClassName FileNIOClient
 * @Description TODO
 * @Author a
 * @Date 2023/6/29 21:05
 */
public class FileNIOClient {

    public static void client() throws IOException {
        FileChannel inFileChannel = FileChannel.open(Paths.get("D:\\windows6.1-KB976932-X64.exe"), StandardOpenOption.READ);
        // 创建与服务端建立连接的 SocketChannel 对象
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("192.168.43.229", 8888));
        // 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long start = System.currentTimeMillis();
        // 读取本地文件，并发送到服务端
        while (inFileChannel.read(buffer) != -1) {
            buffer.rewind();
            socketChannel.write(buffer);
            buffer.clear();
        }
        if (inFileChannel != null) inFileChannel.close();
        if (socketChannel != null) socketChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("客户端发送文件耗时：" + (end - start));
    }

    public static void client2() throws IOException {
        long start = System.currentTimeMillis();
        // 创建与服务端建立连接的 SocketChannel 对象
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("192.168.8.2", 8888));
        FileChannel inFileChannel = FileChannel.open(Paths.get("D:\\ceshi.rar"), StandardOpenOption.READ);
        // 通过 inFileChannel.size() 获取文件的大小，从而在内核地址空间中开辟与文件大小相同的直接缓冲区
        long size = inFileChannel.size();

        long postion = 0 ;

        while (size > 0) {
            long count =inFileChannel.transferTo(postion, size, socketChannel);
            postion += count;

            size -= count;

        }
        if (inFileChannel != null) inFileChannel.close();
        if (socketChannel != null) socketChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("客户端发送文件耗时：" + (end - start));
    }

    public static void main(String[] args) throws IOException {
        client2();
    }
}
