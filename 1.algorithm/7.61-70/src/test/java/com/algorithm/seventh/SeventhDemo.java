package com.algorithm.seventh;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

/**
 * @Author: railgun
 * 2021/2/18 22:10
 * PS:算法61-70题
 */
public class SeventhDemo {

    /**
     * railgun
     * 2021/2/18 22:10
     * PS:旋转链表
     */
    @Test
    public void test61() {
        ListNode l = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        GsonUtil.objectSoutJson(rotateRight(l, 4));
    }

    /**
     * railgun
     * 2021/2/18 22:14
     * PS:先将链表闭合成环
     * 找到相应的位置断开这个环，确定新的链表头和链表尾
     */
    public ListNode rotateRight(ListNode head, int k) {
        // base cases
        if (head == null) return null;
        if (head.next == null) return head;

        // close the linked list into the ring
        ListNode old_tail = head;
        int n;
        for (n = 1; old_tail.next != null; n++)
            old_tail = old_tail.next;
        old_tail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        ListNode new_tail = head;
        for (int i = 0; i < n - k % n - 1; i++)
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;

        return new_head;
    }

    /**
     * railgun
     * 2021/2/21 1:03
     * PS:不同路径
     */
    @Test
    public void test62() {
        System.out.println(uniquePaths(3, 7));
        System.out.println(uniquePaths2(3, 7));
    }

    /**
     * railgun
     * 2021/2/21 1:03
     * PS:动态规划
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    /**
     * railgun
     * 2021/2/21 1:04
     * PS:组合数学
     */
    public int uniquePaths2(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

    /**
     * railgun
     * 2021/2/21 22:58
     * PS:不同路径 Ⅱ
     */
    @Test
    public void test63() {
        int[][] obstacleGrid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    /**
     * railgun
     * 2021/2/21 23:04
     * PS:动态规划
     */
    private int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int[] f = new int[m];
        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int[] ints : obstacleGrid) {
            for (int j = 0; j < m; ++j) {
                if (ints[j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && ints[j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[m - 1];
    }

}
