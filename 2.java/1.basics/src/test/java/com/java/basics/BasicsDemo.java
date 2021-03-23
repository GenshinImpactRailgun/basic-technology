package com.java.basics;

import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @Author: railgun
 * 2021/2/18 0:14
 * PS:basics
 */
public class BasicsDemo {

    /**
     * railgun
     * 2021/3/21 9:17
     * PS:JDK 和 JRE 区别
     */
    @Test
    public void test1() {
        System.out.println("JDK：Java Development Kit 的简称，java 开发工具包，提供了 java 的开发环境和运行环境。");
        System.out.println("JRE：Java Runtime Environment 的简称，java 运行环境，为 java 的运行提供了所需环境。");
        System.out.println("具体来说 JDK 其实包含了 JRE，同时还包含了编译 java 源码的编译器 javac，还包含了很多 java 程序调试和分析的工具。" +
                "简单来说：如果你需要运行 java 程序，只需安装 JRE 就可以了，如果你需要编写 java 程序，需要安装 JDK。");
    }

    /**
     * railgun
     * 2021/2/18 0:19
     * PS:== 和 equals 的区别
     */
    @Test
    public void test2() {
        System.out.println("== 基本类型判断值是否相等；引用类型，判断引用是否相等；");
        System.out.println("equals 本质上就是 ==，但是 String Integer 等包装类重写了 equals 方法，实现的是判断值是否相等");
        int int1 = 2, int2 = 2;
        // 相同
        System.out.println(int1 == int2);
        String s1 = "demo", s2 = "demo";
        // 不同
        System.out.println(s1 == s2);
        // 相同，因为 String 重写了 equals 方法，判断了值是否相同
        System.out.println(s1.equals(s2));
        Demo2 d1 = new Demo2("1"), d2 = new Demo2("1");
        // 不同
        System.out.println(d1 == d2);
        // 不同，没有重写 equals 方法，如果如下方所示，重写了 equals 方法的对象用这种方法判断就就是对的了
        System.out.println(d1.equals(d2));
    }

    private class Demo2 {
        private String name;

        private Demo2(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Demo2 demo2 = (Demo2) o;
            return Objects.equals(name, demo2.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    /**
     * railgun
     * 2021/3/21 9:34
     * PS:两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？
     */
    @Test
    public void test3() {
        System.out.println("不对，两个对象的 hashCode() 相同，equals() 不一定是 true");
        String s1 = "通话", s2 = "重地";
        System.out.println(String.format("s1:%d,s2:%d", s1.hashCode(), s2.hashCode()));
        System.out.println(s1.equals(s2));
    }

    /**
     * railgun
     * 2021/3/21 9:37
     * PS:final 在java 中有何作用
     */
    @Test
    public void test4() {
        System.out.println("final 修饰的类不能被继承");
        System.out.println("final 修饰的方法不能被重写");
        System.out.println("final 修饰的变量叫常量，常量必须初始化，初始化之后值不能被修改");
    }

    /**
     * railgun
     * 2021/3/21 9:38
     * PS:该类无法被继承
     */
    private final class Demo4 {
        public String name;
    }

    /**
     * railgun
     * 2021/3/21 9:41
     * PS:java 中的 Math.round(-1.5) 等于多少？
     */
    @Test
    public void test5() {
        System.out.println(Math.round(-1.5));
        System.out.println("-1 数轴为例，0.5向右取整，如果是 -1.6 结果就是 -2");
    }

    /**
     * railgun
     * 2021/3/21 9:45
     * PS:String 属于基础的数据类型吗？
     */
    @Test
    public void test6() {
        System.out.println("基础类型有：byte、boolean、char、short、int、float、long、double");
        System.out.println("长度：1、1、2、2、4、4、8、8");
        System.out.println("取值范围：-128～127、true或false、从字符型对应的整型数来划分，其表示范围是0～65535、-32768～32767、-2147483648～2147483647、-3.4E38～3.4E38、-9223372036854775808 ~ 9223372036854775807、-1.7E308～1.7E308");
    }

    /**
     * railgun
     * 2021/3/21 9:53
     * PS:java 中操作字符串都有哪些类？它们之间有什么区别？
     */
    @Test
    public void test7() {
        int n = 100000000;
        /*long time = System.currentTimeMillis();
        String string = "";
        for (int i = 0; i < n; i++) {
            string += "railgun";
        }*/
        long time1 = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append("bleach");
        }
        long time2 = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            stringBuffer.append("natural");
        }
        long time3 = System.currentTimeMillis();
        //System.out.println(time1 - time);
        System.out.println(time2 - time1);
        System.out.println(time3 - time2);
    }

    /**
     * railgun
     * 2021/3/23 2:22
     * PS:String str="i"与 String str=new String("i")一样吗？
     */
    @Test
    public void test8() {
        System.out.println("分配到常量池中");
        String s1 = "demo";
        String s2 = new String("demo");
        System.out.println("分配到堆内存中");
    }

    /**
     * railgun
     * 2021/3/23 2:26
     * PS:如何将字符串反转
     */
    @Test
    public void test9() {
        StringBuilder s = new StringBuilder("railgun");
        System.out.println(s);
        s.reverse();
        System.out.println(s);
        s = s.reverse();
        System.out.println(s);
        System.out.println("StringBuilder 和 StringBuffer 的 reverse 方法");
    }

    /**
     * railgun
     * 2021/3/23 2:28
     * PS:String 类的常用方法有哪些
     */
    @Test
    public void test10() {
        String s = "railgun";
        System.out.println(s.indexOf("g"));
        System.out.println(s.charAt(2));
        System.out.println(s.replace("g", "s"));
        System.out.println(s.trim());
        GsonUtil.objectSoutJson(s.split("u"));
        GsonUtil.objectSoutJson(s.getBytes());
        System.out.println(s.length());
        System.out.println(s.toLowerCase());
        System.out.println(s.toUpperCase());
        System.out.println(s.substring(2, 4));
        System.out.println(s.equals("railgun"));
    }

    /**
     * railgun
     * 2021/3/23 19:48
     * PS:抽象了必须要有抽象方法吗？
     */
    @Test
    public void test11() {
        System.out.println("不一定要有抽象方法，如下一个抽象类就没有抽象方法也可以正常运行");
        Demo11 demo11 = new Demo11() {
            @Override
            public void sayHi() {
                super.sayHi();
            }
        };
        demo11.sayHi();
    }

    /**
     * @Author: railgun
     * 2021/3/23 19:50
     * PS:抽象类不一定要有抽象方法
     */
    private abstract class Demo11 {
        public void sayHi() {
            System.out.println("sayHi railgun");
        }
    }

    /**
     * railgun
     * 2021/3/23 19:51
     * PS:抽象类能使用 final 修饰吗？
     */
    @Test
    public void test12() {
        System.out.println("不能，因为抽象类定义出来之后就是为了被继承的，但是被 final 修饰的类不能被继承！");
    }

    /**
     * railgun
     * 2021/3/23 19:52
     * PS:接口和抽象类有啥区别
     */
    @Test
    public void test13() {
        System.out.println("抽象类的子类使用 extends 来继承；接口使用 implements 来实现接口");
        System.out.println("构造函数：抽象类可以有构造函数；接口不能有");
        System.out.println("main 方法：抽象类可以有 main 方法，并且我们能运行它；接口不能有 main 方法");
        System.out.println("抽象类单继承，接口多实现");
        System.out.println("接口方法默认是使用 public 修饰的，抽象类可以自定义修饰符");
    }

    /**
     * railgun
     * 2021/3/23 21:31
     * PS:java io 流分为几种
     */
    @Test
    public void test14() {
        System.out.println("输入流和输出流");
        System.out.println("字节流和字符流");
    }

    /**
     * railgun
     * 2021/3/23 21:32
     * PS:BIO、NIO、AIO 区别
     */
    @Test
    public void test15() {
        System.out.println("BIO：同步阻塞 IO，使用简便，并发处理能力低");
        System.out.println("NIO：同步非阻塞 IO，实现了多路复用");
        System.out.println("AIO：异步非阻塞 IO，异步 IO 操作基于事件和回调机制");
    }

    /**
     * railgun
     * 2021/3/23 21:38
     * PS:IO 详解
     */



}
