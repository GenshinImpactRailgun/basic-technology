package com.java.basics.bio.file;

import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Author: railgun
 * 2021/3/24 23:23
 * PS:
 */
public class Client {
    public static void main(String[] args) {
        String rootPath = Client.class.getClassLoader().getResource("").getPath() + "com/java/basics/bio/file/annex/demo.txt";
        try (
                InputStream is = new FileInputStream(rootPath);
        ) {
            Socket socket = new Socket("127.0.0.1", 9999);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(".txt");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                dos.write(buffer, 0, len);
            }
            dos.flush();
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test0() {
        System.out.println();
        Client client = new Client();
        String rootPath = client.getClass().getClassLoader().getResource("").getPath();
        System.out.println(rootPath + "com/java/basics/bio/file/annex/demo.txt");
    }

}
