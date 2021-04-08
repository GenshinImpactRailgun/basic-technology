package com.java.basics.nio.first;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author: railgun
 * 2021/4/3 18:36
 * PS:服务端
 */
public class Server {

    public static void main(String[] args) {
        try {
            System.out.println("服务端启动");
            // 1、获取通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            // 2、切换为非阻塞模式
            ssc.configureBlocking(false);
            // 3、绑定连接的端口
            ssc.bind(new InetSocketAddress(6666));
            // 4、获取选择器
            Selector selector = Selector.open();
            // 5、将通道注册到选择器上，并且开始指定监听接收事件
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            // 6、轮询监听已经就绪好的事件
            while (selector.select() > 0) {
                System.out.println("开始一轮事件处理");
                // 7、获取选择器中所有注册的通道中已经就绪好的事件
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                // 8、遍历所有事件
                while (it.hasNext()) {
                    // 提起当前事件
                    SelectionKey sk = it.next();
                    // 9、判断事件具体的属性
                    if (sk.isAcceptable()) {
                        // 10、直接获取当前接入的客户端通道
                        SocketChannel sc = ssc.accept();
                        // 11、切换成非阻塞模式
                        sc.configureBlocking(false);
                        // 12、将本客户端的通道注册到选择器中
                        sc.register(selector, SelectionKey.OP_READ);
                    } else if (sk.isReadable()) {
                        // 13、获取当前选择器上的读就绪事件
                        SocketChannel sc = (SocketChannel) sk.channel();
                        // 14、读取数据
                        ByteBuffer bb = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = sc.read(bb)) > 0) {
                            // 重置 position 为 0；limit 设置为已有值的最大长度；
                            bb.flip();
                            System.out.println(new String(bb.array(), 0, len));
                            // 清除之前的数据【这一段代码不需要】【教程错了】
                            //bb.clear();
                        }
                    }
                    // 处理完毕之后需要移除当前事件
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
