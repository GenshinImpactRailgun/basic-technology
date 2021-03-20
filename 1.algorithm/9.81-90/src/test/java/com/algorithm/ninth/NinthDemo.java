package com.algorithm.ninth;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: railgun
 * 2021/2/18 22:23
 * PS:算法81-90题
 */
public class NinthDemo {

    /**
     * railgun
     * 2021/2/18 22:23
     * PS:搜索旋转排序数组 Ⅱ
     */
    @Test
    public void test81() {
        int[] nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        System.out.println(search(nums, 3));
    }

    /**
     * railgun
     * 2021/2/18 22:32
     * PS:二分法
     */
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target || nums[left] == target || nums[right] == target) {
                return true;
            } else if (nums[mid] > target && target > nums[left]) {
                right = mid;
            } else if (nums[mid] < target && target < nums[right]) {
                left = mid;
            }
            left++;
            right--;
        }
        return false;
    }

    /**
     * railgun
     * 2021/2/21 1:10
     * PS:删除排序链表中的重复元素 II
     */
    @Test
    public void test82() {
        GsonUtil.objectSoutJson(deleteDuplicates(new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3)))))));
    }

    /**
     * railgun
     * 2021/2/21 1:12
     * PS:熟悉链表操作
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        ListNode cur = prev.next;
        while (cur != null) {
            int curRepeatNum = 0;
            ListNode difNode = cur;
            // 找到和cur指向的结点值不同的结点
            while (difNode != null && difNode.val == cur.val) {
                curRepeatNum++;
                difNode = difNode.next;
            }
            // 如果curRepeatNum的值大于1，则表示cur指向的结点重复出现了
            if (curRepeatNum > 1) {
                prev.next = difNode;
            } else {
                // cur指向的结点没有重复出现，则将变量prev指向cur所指向的结点
                prev = cur;
            }
            cur = difNode;
        }
        return dummyHead.next;
    }

    /**
     * railgun
     * 2021/2/21 23:25
     * PS:
     */
    @Test
    public void test83() {
        ListNode l = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        GsonUtil.objectSoutJson(deleteDuplicates83(l));
        ListNode l2 = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        GsonUtil.objectSoutJson(deleteDuplicates832(l2));
    }

    /**
     * railgun
     * 2021/2/21 23:37
     * PS:哑结点
     */
    private ListNode deleteDuplicates83(ListNode l) {
        ListNode result = new ListNode(Integer.MAX_VALUE), target = result;
        while (null != l) {
            if (target.val != l.val) {
                target.next = new ListNode(l.val);
                target = target.next;
            }
            l = l.next;
        }
        return result.next;
    }

    /**
     * railgun
     * 2021/2/21 23:37
     * PS:直接操作链表对象【速度比哑结点方法快】
     */
    private ListNode deleteDuplicates832(ListNode l) {
        ListNode result = l;
        while (null != l && null != l.next) {
            if (l.val == l.next.val) {
                l.next = l.next.next;
            } else {
                l = l.next;
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/25 1:19
     * PS:柱状图中最大的矩形
     */
    @Test
    public void test84() {
        int[] nums = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(nums));
        System.out.println(largestRectangleArea2(nums));
        System.out.println(largestRectangleArea3(nums));
        System.out.println(largestRectangleArea4(nums));
    }

    /**
     * railgun
     * 2021/2/25 20:10
     * PS:暴力方法，枚举宽，用双重 for 循环
     */
    private int largestRectangleArea(int[] nums) {
        int result = 0;
        int minHeight;
        for (int i = 0; i < nums.length; i++) {
            minHeight = nums[i];
            for (int j = i; j < nums.length; j++) {
                minHeight = Math.min(minHeight, nums[j]);
                result = Math.max(result, minHeight * (j - i + 1));
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/25 20:17
     * PS:暴力方法，枚举高，然后用 while 去确定左右边界
     */
    private int largestRectangleArea2(int[] nums) {
        int result = 0, n = nums.length, height;
        for (int i = 0; i < n; i++) {
            height = nums[i];
            int left = i, right = i;
            while (left - 1 >= 0 && nums[left - 1] >= height) {
                left--;
            }
            while (right + 1 <= n - 1 && nums[right + 1] >= height) {
                right++;
            }
            result = Math.max(height * (right - left + 1), result);
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/25 21:16
     * PS:栈的使用
     */
    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int area = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]) {
                int height = heights[stack.removeLast()];

                while (!stack.isEmpty() && heights[stack.peekLast()] == height) {
                    stack.removeLast();
                }

                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peekLast() - 1;
                }

                area = Math.max(area, width * height);
            }
            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            int height = heights[stack.removeLast()];

            while (!stack.isEmpty() && heights[stack.peekLast()] == height) {
                stack.removeLast();
            }

            int width;
            if (stack.isEmpty()) {
                width = len;
            } else {
                width = len - stack.peekLast() - 1;
            }

            area = Math.max(area, width * height);
        }
        return area;
    }

    /**
     * railgun
     * 2021/2/25 21:17
     * PS:栈数据结构思想的优化应用
     */
    public int largestRectangleArea4(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int area = 0;
        int[] newHeights = new int[len + 2];
        for (int i = 0; i < len; i++) {
            newHeights[i + 1] = heights[i];
        }
        len += 2;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            while (heights[stack.peekLast()] > heights[i]) {
                int height = heights[stack.removeLast()];
                int width = i - stack.peekLast() - 1;
                area = Math.max(area, width * height);
            }
            stack.addLast(i);
        }
        return area;
    }

    /**
     * railgun
     * 2021/3/2 21:37
     * PS:最大矩形
     */
    @Test
    public void test85() {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalRectangle(matrix));
        char[][] matrix2 = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalRectangle2(matrix2));
    }

    /**
     * railgun
     * 2021/3/2 22:07
     * PS:使用柱状图的优化暴力方法
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i - k + 1) * width);
                }
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    /**
     * railgun
     * 2021/3/2 22:08
     * PS:单调栈
     */
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[m];
            int[] down = new int[m];

            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    /**
     * railgun
     * 2021/3/17 0:33
     * PS:分隔链表
     */
    @Test
    public void test86() {
        ListNode head = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        int x = 3;
        GsonUtil.objectSoutJson(partition(head, x));
    }

    /**
     * railgun
     * 2021/3/17 0:36
     * PS:维护两个链表的思路
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0), smallTarget = small;
        ListNode large = new ListNode(0), largeTarget = large;
        while (head != null) {
            if (head.val < x) {
                smallTarget.next = new ListNode(head.val);
                smallTarget = smallTarget.next;
            } else {
                largeTarget.next = new ListNode(head.val);
                largeTarget = largeTarget.next;
            }
            head = head.next;
        }
        smallTarget.next = large.next;
        return small.next;
    }

    /**
     * railgun
     * 2021/3/20 9:17
     * PS:扰乱字符串
     */
    @Test
    public void test87() {
        String s1 = "abcde", s2 = "caebd";
        System.out.println(isScramble(s1, s2));
    }

    /**
     * railgun
     * 2021/3/20 17:50
     * PS:动态规划的思想，状态转移方程和函数定义有点难
     */
    public boolean isScramble(String s1, String s2) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int n = s1.length();
        int m = s2.length();
        if (n != m) {
            return false;
        }
        boolean[][][] dp = new boolean[n][n][n + 1];
        // 初始化单个字符的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = chs1[i] == chs2[j];
            }
        }

        // 枚举区间长度 2～n
        for (int len = 2; len <= n; len++) {
            // 枚举 S 中的起点位置
            for (int i = 0; i <= n - len; i++) {
                // 枚举 T 中的起点位置
                for (int j = 0; j <= n - len; j++) {
                    // 枚举划分位置
                    for (int k = 1; k <= len - 1; k++) {
                        // 第一种情况：S1 -> T1, S2 -> T2
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        // 第二种情况：S1 -> T2, S2 -> T1
                        // S1 起点 i，T2 起点 j + 前面那段长度 len-k ，S2 起点 i + 前面长度k
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }

}
