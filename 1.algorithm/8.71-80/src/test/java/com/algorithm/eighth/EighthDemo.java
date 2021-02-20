package com.algorithm.eighth;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @Author: railgun
 * 2021/2/18 22:16
 * PS:算法71-80题
 */
public class EighthDemo {

    /**
     * railgun
     * 2021/2/18 22:17
     * PS:简化路径
     */
    @Test
    public void test71() {
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }

    /**
     * railgun
     * 2021/2/18 22:21
     * PS:栈解决
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] items = path.split("/");
        for (String item : items) {
            if (item.isEmpty() || item.equals(".")) {
                continue;
            }
            if (item.equals("..")) {
                if (!stack.empty()) stack.pop();
            } else {
                stack.push(item);
            }
        }
        return "/" + String.join("/", stack);
    }

    /**
     * railgun
     * 2021/2/21 1:07
     * PS:编辑距离
     */
    @Test
    public void test72() {
        String s1 = "intention";
        String s2 = "execution";
        System.out.println(minDistance(s1, s2));
    }

    /**
     * railgun
     * 2021/2/21 1:08
     * PS:动态规划
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

}
