package com.java.basics.bio.file;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String suffix = dis.readUTF();
            String rootPath = ServerThread.class.getClassLoader().getResource("").getPath() + "com/java/basics/bio/file/annex/" + UUID.randomUUID().toString() + suffix;
            OutputStream os = new FileOutputStream(rootPath);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = dis.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }
}
