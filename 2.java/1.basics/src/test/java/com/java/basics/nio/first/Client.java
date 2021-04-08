package com.java.basics.nio.first;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @Author: railgun
 * 2021/4/3 19:03
 * PS:客户端
 */
public class Client {

    public static void main(String[] args) {
        try {
            // 1、获取通道
            SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));
            // 2、切换成非阻塞模式
            sc.configureBlocking(false);
            // 3、分配缓冲区大小
            ByteBuffer bb = ByteBuffer.allocate(1024);
            // 4、发送数据给服务器
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("请说：");
                bb.put(("railgun：" + scanner.nextLine()).getBytes());
                bb.flip();
                sc.write(bb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
