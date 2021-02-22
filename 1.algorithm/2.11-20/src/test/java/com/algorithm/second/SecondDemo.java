package com.algorithm.second;

import org.junit.jupiter.api.Test;

/**
 * @Author: railgun
 * 2021/2/18 12:56
 * PS:算法11-20题
 */
public class SecondDemo {

    /**
     * railgun
     * 2021/2/18 17:17
     * PS:盛最多水的容器
     */
    @Test
    public void test11() {
        int[] nums = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(nums));
        System.out.println(maxArea2(nums));
    }

    /**
     * railgun
     * 2021/2/18 17:46
     * PS:双指针
     */
    private int maxArea(int[] nums) {
        int result = 0, l = 0, r = nums.length - 1;
        while (l < r) {
            result = Math.max(result, Math.min(nums[l], nums[r]) * (r - l));
            if (nums[l] < nums[r]) {
                l++;
            } else {
                r--;
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/18 19:14
     * PS:暴力解法
     */
    private int maxArea2(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                result = Math.max(result, Math.min(nums[i], nums[j]) * (j - i));
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/20 17:39
     * PS:整数转罗马数字
     */
    @Test
    public void test12() {
        System.out.println(intToRoman(1994));
    }

    /**
     * railgun
     * 2021/2/20 17:48
     * PS:贪心算法
     */
    private String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length && num >= 0; i++) {
            while (values[i] <= num) {
                num -= values[i];
                result.append(symbols[i]);
            }
        }
        return result.toString();
    }

    /**
     * railgun
     * 2021/2/21 11:27
     * PS:罗马数字转整数
     */
    @Test
    public void test13() {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
        System.out.println(romanToInt2(s));
        System.out.println(romanToInt3(s));
    }

    /**
     * railgun
     * 2021/2/21 11:42
     * PS:贪心算法
     */
    private int romanToInt(String s) {
        StringBuilder sb = new StringBuilder(s);
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int result = 0;
        for (int i = 0; i < symbols.length; i++) {
            while (sb.length() > 0 && 0 == sb.indexOf(symbols[i])) {
                result += values[i];
                sb.delete(0, symbols[i].length());
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/21 11:46
     * PS:加减思想
     */
    private int romanToInt2(String s) {
        int result = 0, pre = romanGetValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int n = romanGetValue(s.charAt(i));
            if (pre < n) {
                result -= pre;
            } else {
                result += pre;
            }
            pre = n;
        }
        result += pre;
        return result;
    }

    private int romanGetValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /**
     * railgun
     * 2021/2/21 13:00
     * PS:替换字符串思想
     */
    private int romanToInt3(String s) {
        s = s.replace("IV", "a");
        s = s.replace("IX", "b");
        s = s.replace("XL", "c");
        s = s.replace("XC", "d");
        s = s.replace("CD", "e");
        s = s.replace("CM", "f");
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result += romanGetValue3(s.charAt(i));
        }
        return result;
    }

    private int romanGetValue3(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            case 'a':
                return 4;
            case 'b':
                return 9;
            case 'c':
                return 40;
            case 'd':
                return 90;
            case 'e':
                return 400;
            case 'f':
                return 900;
            default:
                return 0;
        }
    }

    /**
     * railgun
     * 2021/2/22 21:29
     * PS:最长公共前缀
     */
    @Test
    public void test14() {
        String[] strs = new String[]{"abc", "abd", "aaaaa"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefix2(strs));
    }

    /**
     * railgun
     * 2021/2/22 21:53
     * PS:纵向扫描
     */
    private String longestCommonPrefix(String[] strs) {
        if (null == strs || 0 == strs.length) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * railgun
     * 2021/2/22 22:06
     * PS:横向扫描
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

}
