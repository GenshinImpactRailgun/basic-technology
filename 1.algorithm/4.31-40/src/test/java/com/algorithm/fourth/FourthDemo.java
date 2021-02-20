package com.algorithm.fourth;

import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: railgun
 * 2021/2/18 19:32
 * PS:算法31-40题
 */
public class FourthDemo {

    /**
     * railgun
     * 2021/2/18 19:33
     * PS:下一个排列
     */
    @Test
    public void test31() {
        int[] nums = new int[]{5, 1, 1};
        nextPermutation(nums);
        GsonUtil.objectSoutJson(nums);
    }

    /**
     * railgun
     * 2021/2/18 19:48
     * PS:两遍扫描
     * 注意数组中有相同值的情况
     */
    private void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int right = nums.length - 1;
        while (start < right) {
            swap(nums, start, right);
            start++;
            right--;
        }
    }

    /**
     * railgun
     * 2021/2/21 0:12
     * PS:最长有效括号
     */
    @Test
    public void test32() {
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses2(")()())"));
        System.out.println(longestValidParentheses3(")()())"));
    }

    /**
     * railgun
     * 2021/2/21 0:14
     * PS:动态规划
     */
    private int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * railgun
     * 2021/2/21 0:16
     * PS:栈
     */
    public int longestValidParentheses2(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /**
     * railgun
     * 2021/2/21 0:27
     * PS:
     */
    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

}
