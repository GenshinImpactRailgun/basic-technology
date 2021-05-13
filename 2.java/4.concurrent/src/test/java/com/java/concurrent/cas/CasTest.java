package com.java.concurrent.cas;

/**
 * @Author: railgun
 * 2021/5/12 20:44
 * PS: CAS 配合 voliation
 **/
public class CasTest {

    /**
     * 2021/5/12 20:45 @railgun 保证 count 的可见性
     **/
    private volatile int count;

    public static void main(String[] args) {
        System.out.println("有点复杂，稍后再写，耶嘿");
    }

}
