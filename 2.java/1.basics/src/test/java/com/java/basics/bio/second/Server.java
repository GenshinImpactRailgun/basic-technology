package com.java.basics.bio.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            // 2、监听客户端的 Socket 请求
            Socket s = ss.accept();
            // 3、从 Socket 管道中获取一个字节输出流对象
            InputStream inputStream = s.getInputStream();
            // 4、缓冲字节输入流包装秤缓冲字节输入流
            //BufferedInputStream bis = new BufferedInputStream(inputStream);
            // 4、缓冲字节输入流包装成缓冲字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String msg = "";
            while ((msg = br.readLine()) != null) {
                System.out.println("服务器接收到：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
