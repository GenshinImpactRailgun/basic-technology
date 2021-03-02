package com.algorithm.first;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: railgun
 * 2021/2/15 17:32
 * PS:力扣1-10题
 */
public class FirstDemo {

    /**
     * railgun
     * 2021/2/18 15:28
     * PS:两数之和
     */
    @Test
    public void test1() {
        int target = 9;
        int[] nums = new int[]{5, 7, 3, 2, 9};
        GsonUtil.objectSoutJson(twoSum(target, nums));
        GsonUtil.objectSoutJson(twoSum2(target, nums));
    }

    /**
     * railgun
     * 2021/2/18 17:46
     * PS:Map 这一种数据类型的使用
     */
    private int[] twoSum(int target, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * railgun
     * 2021/2/18 19:17
     * PS:暴力解法
     */
    private int[] twoSum2(int target, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * railgun
     * 2021/2/20 17:02
     * PS:两数相加
     */
    @Test
    public void test2() {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        GsonUtil.objectSoutJson(addTwoNumbers(l1, l2));
    }

    /**
     * railgun
     * 2021/2/20 17:38
     * PS:哑结点的使用
     */
    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1), target = result;
        int carry = 0;
        while (null != l1 || null != l2) {
            int value1 = 0;
            if (null != l1) {
                value1 = l1.val;
                l1 = l1.next;
            }
            int value2 = 0;
            if (null != l2) {
                value2 = l2.val;
                l2 = l2.next;
            }
            int sum = value1 + value2 + carry;
            target.next = new ListNode(sum % 10);
            target = target.next;
            carry = sum / 10;
        }
        if (carry > 0) {
            target.next = new ListNode(carry);
        }
        return result.next;
    }

    /**
     * railgun
     * 2021/2/21 10:24
     * PS:无重复的最长子串
     */
    @Test
    public void test3() {
        String s = "djfdkd";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     * railgun
     * 2021/2/21 10:26
     * PS:滑动窗口
     */
    private int lengthOfLongestSubstring(String s) {
        int result = 0, r = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (0 != i) {
                set.remove(s.charAt(i - 1));
            }
            while (r < s.length() && !set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                r++;
            }
            result = Math.max(result, r - i);
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/22 21:10
     * PS:寻找两个正序数组的中位数
     */
    @Test
    public void test4() {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /**
     * railgun
     * 2021/2/22 21:26
     * PS:二分查找
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    public int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        int m = nums1.length;
        int n = nums2.length;
        if (i >= m) {
            return nums2[j + k - 1];
        }
        if (j >= n) {
            return nums1[i + k - 1];
        }
        if (1 == k) {
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < m) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < n) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }

    /**
     * railgun
     * 2021/2/25 23:17
     * PS:找出最长回文字符串
     */
    @Test
    public void test5() {
        String s = "babad";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome2(s));
    }

    /**
     * railgun
     * 2021/2/25 23:18
     * PS:动态规划
     */
    private String longestPalindrome(String s) {
        String result = "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int r = i + l;
                if (0 == l) {
                    dp[i][r] = true;
                } else {
                    boolean b = s.charAt(i) == s.charAt(r);
                    if (1 == l) {
                        dp[i][r] = b;
                    } else {
                        dp[i][r] = b && dp[i + 1][r - 1];
                    }
                }
                if (dp[i][r] && l + 1 > result.length()) {
                    result = s.substring(i, r + 1);
                }
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/26 0:31
     * PS:中心扩展算法
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    /**
     * railgun
     * 2021/3/2 22:30
     * PS:Z字形变换
     */
    @Test
    public void test6() {
        String s = "PAYPALISHIRING";
        int n = 3;
        System.out.println(convert(s, n));
    }

    /**
     * railgun
     * 2021/3/2 23:21
     * PS:按行排序
     */
    public String convert(String s, int n) {
        StringBuilder[] array = new StringBuilder[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = new StringBuilder();
        }
        int length = s.length();
        int row = 0, add = 1;
        for (int i = 0; i < length; i++) {
            array[row].append(s.charAt(i));
            if (add > 0) {
                if (row + add > n - 1) {
                    add = -1;
                }
            } else {
                if (row + add < 0) {
                    add = 1;
                }
            }
            add = (n == 1) ? 0 : add;
            row += add;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
        }
        return result.toString();
    }
    
    /**
     * railgun
     * 2021/3/2 23:21
     * PS:按行访问
     */
    public String convert2(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

}
