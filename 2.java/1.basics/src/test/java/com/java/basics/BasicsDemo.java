package com.java.basics;

import org.junit.jupiter.api.Test;

/**
 * @Author: railgun
 * 2021/2/18 0:14
 * PS:basics
 */
public class BasicsDemo {

    private String railgun = "railgun";

    /**
     * railgun
     * 2021/2/18 0:19
     * PS:== 和 equals 的区别
     */
    @Test
    public void test0() {
        String str1 = "railgun";
        String str2 = new String("railgun");
        String str3 = railgun;
        System.out.println(str1.equals(str2));
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str3);
    }

}
