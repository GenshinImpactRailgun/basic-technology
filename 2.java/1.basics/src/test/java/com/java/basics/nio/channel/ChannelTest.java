package com.java.basics.nio.channel;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: railgun
 * 2021/4/3 17:27
 * PS:channel API
 */
public class ChannelTest {

    @Test
    public void test1() {
        try {
            // 1、字节输出流通向目标文件
            FileOutputStream fos = new FileOutputStream("data01.txt");
            // 2、得到字节输出流对应的通道
            FileChannel fc = fos.getChannel();
            // 3、分配缓冲区
            ByteBuffer bb = ByteBuffer.allocate(1024);
            bb.put("railgun".getBytes());
            // 4、将缓冲区切换成写出模式
            bb.flip();
            fc.write(bb);
            fc.close();
            System.out.println("写出文件成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            // 1、定义一个文字节输入流与源文件接通
            FileInputStream fs = new FileInputStream("data01.txt");
            // 2、得到文件字节输入流对应的通道
            FileChannel fc = fs.getChannel();
            // 3、定义一个缓冲区
            ByteBuffer bb = ByteBuffer.allocate(1024);
            // 4、读取数据到缓冲区
            fc.read(bb);
            bb.flip();
            // 5、读取缓冲区中的数据并输出即可
            System.out.println(new String(bb.array(), 0, bb.remaining()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            File file = new File("D:\\ai39\\centos7\\centos7.txt");
            File targetFile = new File("D:\\ai39\\centos7\\centos7_new.txt");
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(targetFile);
            FileChannel fci = fis.getChannel();
            FileChannel fco = fos.getChannel();
            ByteBuffer bb = ByteBuffer.allocate(1024);
            while (true) {
                // 先清空缓冲区，再写入数据到缓冲区，不然每次都是第一次写入的数据
                bb.clear();
                // 开始读数据
                int flag = fci.read(bb);
                if (flag == -1) {
                    break;
                }
                // 读取数据完成，切换成可读模式
                bb.flip();
                // 写出数据到拷贝目标文件的 FileChannel 中
                fco.write(bb);
            }
            fci.close();
            fco.close();
            System.out.println("复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        try {
            // 1、字节输入管道
            FileChannel fci = new FileInputStream("data01.txt").getChannel();
            // 2、字节输出管道
            FileChannel fco = new FileOutputStream("data02.txt").getChannel();
            // 3、定义多个缓冲区
            ByteBuffer[] bArray = {ByteBuffer.allocate(4), ByteBuffer.allocate(1024)};
            // 4、从通道中读取数据到分散的缓冲区中
            fci.read(bArray);
            // 5、检查一下是否读到了
            for (ByteBuffer bb : bArray) {
                // 切换到可读模式
                bb.flip();
                System.out.println(new String(bb.array(), 0, bb.remaining()));
            }
            // 6、聚集到字节输出通道
            fco.write(bArray);
            fci.close();
            fco.close();
            System.out.println("复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        try {
            FileChannel fci = new FileInputStream("data01.txt").getChannel();
            FileChannel fco = new FileOutputStream("data03.txt").getChannel();
            fco.transferFrom(fci, fci.position(), fci.size());
            fci.close();
            fco.close();
            System.out.println("transferFrom 完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() {
        try {
            FileChannel fci = new FileInputStream("data01.txt").getChannel();
            FileChannel fco = new FileOutputStream("data04.txt").getChannel();
            fci.transferTo(fci.position(), fci.size(), fco);
            fci.close();
            fco.close();
            System.out.println("transferTo 完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
