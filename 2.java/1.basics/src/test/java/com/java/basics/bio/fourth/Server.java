package com.java.basics.bio.fourth;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: railgun
 * 2021/3/24 0:17
 * PS:
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("服务端启动");
            // 1、定义一个 ServerSocket 对象进行服务端的端口诸恶
            ServerSocket ss = new ServerSocket(9999);
            ServerPool serverPool = new ServerPool(6, 10);
            // 2、定义一个死循环，不断的接收客户端的请求
            while (true) {
                // 2、监听客户端的 Socket 请求
                Socket s = ss.accept();
                Runnable serverRunnable = new ServerRunnable(s);
                serverPool.execute(serverRunnable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
