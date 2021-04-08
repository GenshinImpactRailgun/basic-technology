package com.java.basics.nio.buffer;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * @Author: railgun
 * 2021/4/3 15:34
 * PS:Buffer 的常用 API 进行测试
 */
public class BufferTest {

    @Test
    public void test1(){
        // 1、分配一个，容量设置为 10
        ByteBuffer bb = ByteBuffer.allocate(10);
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        System.out.println("-------------------------");

        // 2、put 往缓冲区中加入数据
        String name = "railgun";
        bb.put(name.getBytes());
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        System.out.println("-------------------------");


        // 3、flip 将缓冲区设置为当前位置，并将当前位置设置为 0；可读模式
        bb.flip();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        System.out.println("-------------------------");

        char c = (char) bb.get();
        System.out.println(c);
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        System.out.println("-------------------------");
    }

    @Test
    public void test2(){
        // 1、分配一个，容量设置为 10
        ByteBuffer bb = ByteBuffer.allocate(10);
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        System.out.println("-------------------------");

        // 2、put 往缓冲区中加入数据
        String name = "railgun";
        bb.put(name.getBytes());
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        System.out.println("-------------------------");

        bb.clear();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        System.out.println((char)bb.get());
        System.out.println("-------------------------");

        ByteBuffer bb1 = ByteBuffer.allocate(10);
        bb1.put("railgun".getBytes());
        bb1.flip();
        byte[] b = new byte[2];
        bb1.get(b);
        String rs = new String(b);
        System.out.println(rs);
        System.out.println(bb1.position());
        System.out.println(bb1.limit());
        System.out.println(bb1.capacity());
        System.out.println("-------------------------");

        // 标记
        bb1.mark();
        byte[] b2 = new byte[3];
        bb1.get(b2);
        System.out.println(new String(b2));
        System.out.println(bb1.position());
        System.out.println(bb1.limit());
        System.out.println(bb1.capacity());
        System.out.println("-------------------------");

        // 重新定位到标记位置
        bb1.reset();
        if(bb1.hasRemaining()){
            System.out.println(bb1.remaining());
        }
    }

    @Test
    public void test3(){
        ByteBuffer bb = ByteBuffer.allocate(1024);
        System.out.println(bb.isDirect());

        ByteBuffer bb1 =ByteBuffer.allocateDirect(1024);
        System.out.println(bb1.isDirect());
    }

}
