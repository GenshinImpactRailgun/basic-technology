package com.java.basics.bio.third;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 3、从 Socket 管道中获取一个字节输出流对象
            InputStream inputStream = socket.getInputStream();
            // 4、缓冲字节输入流包装秤缓冲字节输入流
            //BufferedInputStream bis = new BufferedInputStream(inputStream);
            // 4、缓冲字节输入流包装成缓冲字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String msg = "";
            while (true) {
                if ((msg = br.readLine()) == null) break;
                System.out.println("服务器接收到：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }
}
