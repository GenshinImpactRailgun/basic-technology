package com.java.basics.bio.itheima;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = br.readLine()) != null) {
                sendMsgToAllClient(msg);
            }
        } catch (IOException e) {
            Server.allSocketOnline.remove(socket);
            e.printStackTrace();
        }
        super.run();
    }

    private void sendMsgToAllClient(String msg) {
        Server.allSocketOnline.forEach(item -> {
            try {
                PrintStream ps = new PrintStream(item.getOutputStream());
                ps.println(msg);
                ps.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
