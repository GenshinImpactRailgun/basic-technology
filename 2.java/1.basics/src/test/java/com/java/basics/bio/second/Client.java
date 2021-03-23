package com.java.basics.bio.second;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: railgun
 * 2021/3/24 0:17
 * PS:
 */
public class Client {
    public static void main(String[] args) {
        try {
            // 1、创建 Socket 对象请求服务端的连接
            Socket s = new Socket("127.0.0.1", 9999);
            // 2、Socket 对象中获取一个字节输出流
            OutputStream os = s.getOutputStream();
            // 3、把字节输出流包装成打印流
            PrintStream printStream = new PrintStream(os);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("请说：");
                String msg = scanner.nextLine();
                printStream.println(msg);
                printStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
