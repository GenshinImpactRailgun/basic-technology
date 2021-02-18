package com.algorithm.tenth;

import org.junit.jupiter.api.Test;

/**
 * @Author: railgun
 * 2021/2/18 22:34
 * PS:算法91-100
 */
public class TenthDemo {

    /**
     * railgun
     * 2021/2/18 22:35
     * PS:解码方法
     */
    @Test
    public void test91() {
        System.out.println(numDecodings("12"));
    }

    /**
     * railgun
     * 2021/2/18 22:42
     * PS:动态规划
     */
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1; //0个数 一种
        dp[1] = s.charAt(0) == '0' ? 0 : 1; //1个数 一种
        //dp数组中，下标都+1，表示字符个数为下标时的方法总数
        for (int i = 1; i < len; i++) {
            //10-26
            if ((s.charAt(i - 1) == '1') || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {
                //10或20
                if (s.charAt(i) == '0')
                    dp[i + 1] = dp[i - 2 + 1];

                else
                    dp[i + 1] = dp[i - 1 + 1] + dp[i - 2 + 1];
            } else {
                //比如40，50
                if (s.charAt(i) == '0')
                    return 0;

                else
                    dp[i + 1] = dp[i - 1 + 1];
            }
        }
        return dp[len];
    }

}
