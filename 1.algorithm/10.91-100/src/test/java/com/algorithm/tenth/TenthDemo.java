package com.algorithm.tenth;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
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

    /**
     * railgun
     * 2021/2/21 1:13
     * PS:反转链表 II
     */
    @Test
    public void test92() {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverseBetween(l1, 2, 4);
        GsonUtil.objectSoutJson(l1);
        ListNode l2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverseBetween(l2, 2, 4);
        GsonUtil.objectSoutJson(l2);
    }

    private boolean stop;
    private ListNode left;

    /**
     * railgun
     * 2021/2/21 1:15
     * PS:递归
     */
    public void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    /**
     * railgun
     * 2021/2/21 1:19
     * PS:迭代链接反转
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }

}
