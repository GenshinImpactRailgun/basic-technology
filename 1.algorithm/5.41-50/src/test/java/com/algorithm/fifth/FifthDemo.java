package com.algorithm.fifth;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: railgun
 * 2021/2/18 20:26
 * PS:算法41-50题
 */
public class FifthDemo {

    /**
     * railgun
     * 2021/2/18 20:27
     * PS:缺失的第一个正整数
     */
    @Test
    public void test41() {
        int[] nums = new int[]{3, 4, -1, 1};
        System.out.println(firstMissingPositive(nums));
        int[] nums2 = new int[]{3, 4, -1, 1};
        System.out.println(firstMissingPositive2(nums2));
    }

    /**
     * railgun
     * 2021/2/18 21:56
     * PS:哈希表
     */
    private int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * railgun
     * 2021/2/18 22:03
     * PS:置换
     */
    private int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * railgun
     * 2021/2/21 0:27
     * PS:接雨水
     */
    @Test
    public void test42() {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
        System.out.println(trap2(height));
        System.out.println(trap3(height));
        System.out.println(trap4(height));
    }

    /**
     * railgun
     * 2021/2/21 0:30
     * PS:暴力解法
     */
    private int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }

    /**
     * railgun
     * 2021/2/21 0:34
     * PS:动态编程
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    /**
     * railgun
     * 2021/2/21 0:36
     * PS:栈
     */
    public int trap3(int[] height) {
        int ans = 0, current = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }

    /**
     * railgun
     * 2021/2/21 0:38
     * PS:双指针
     */
    public int trap4(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    ans += (left_max - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }

    /**
     * railgun
     * 2021/2/21 15:28
     * PS:字符串相乘
     */
    @Test
    public void test43() {
        String s1 = "123456789";
        String s2 = "987654321";
        System.out.println(multiply(s1, s2));
        System.out.println(multiply2(s1, s2));
        System.out.println(multiply3(s1, s2));
    }

    /**
     * railgun
     * 2021/2/21 19:26
     * PS:做加法
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    /**
     * railgun
     * 2021/2/21 19:28
     * PS:做乘法
     */
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    /**
     * railgun
     * 2021/2/21 19:28
     * PS:快速傅里叶变换
     */
    public String multiply3(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        //类比，三位数*三位数最多6位，最少5位
        int prod[] = new int[num1.length() + num2.length() - 1];
        for (int i = 0; i < num1.length() + num2.length() - 1; i++) {
            for (int j = Math.max(0, i + 1 - num1.length()); j <= Math.min(i, num2.length() - 1); j++) {
                prod[i] += (num1.charAt(i - j) - '0') * (num2.charAt(j) - '0');
            }
        }
        StringBuilder ans = new StringBuilder();
        int jinwei = 0;
        for (int i = num1.length() + num2.length() - 2; i >= 0; i--) {
            int sum = jinwei + prod[i];
            ans.append(sum % 10);
            jinwei = sum / 10;
        }
        if (jinwei > 0) {
            ans.append(jinwei);
        }
        return ans.reverse().toString();
    }

    /**
     * railgun
     * 2021/2/23 1:28
     * PS:通配符匹配
     */
    @Test
    public void test44() {
        String s = "acbcdddbc";
        String p = "a?b*";
        System.out.println(isMatch(s, p));
        System.out.println(isMatch2(s, p));
    }

    /**
     * railgun
     * 2021/2/23 1:30
     * PS:动态规划
     */
    private boolean isMatch(String s, String p) {
        boolean[][] a = new boolean[s.length() + 1][p.length() + 1];
        a[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if ('*' == p.charAt(i - 1)) {
                a[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if ('*' == p.charAt(j - 1)) {
                    a[i][j] = a[i - 1][j] || a[i][j - 1];
                } else if ('?' == p.charAt(j - 1) || s.charAt(i - 1) == p.charAt(j - 1)) {
                    a[i][j] = a[i - 1][j - 1];
                }
            }
        }
        return a[s.length()][p.length()];
    }

    /**
     * railgun
     * 2021/2/23 23:16
     * PS:贪心算法
     */
    public boolean isMatch2(String s, String p) {
        int sRight = s.length(), pRight = p.length();
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                --sRight;
                --pRight;
            } else {
                return false;
            }
        }

        if (pRight == 0) {
            return sRight == 0;
        }

        int sIndex = 0, pIndex = 0;
        int sRecord = -1, pRecord = -1;

        while (sIndex < sRight && pIndex < pRight) {
            if (p.charAt(pIndex) == '*') {
                ++pIndex;
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                ++sIndex;
                ++pIndex;
            } else if (sRecord != -1 && sRecord + 1 < sRight) {
                ++sRecord;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                return false;
            }
        }

        return allStars(p, pIndex, pRight);
    }

    public boolean allStars(String str, int left, int right) {
        for (int i = left; i < right; ++i) {
            if (str.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    public boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }

}
