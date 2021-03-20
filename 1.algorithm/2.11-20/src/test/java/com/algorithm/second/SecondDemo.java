package com.algorithm.second;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    /**
     * railgun
     * 2021/2/26 1:01
     * PS:三数之和
     */
    @Test
    public void test15() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        GsonUtil.objectSoutJson(threeSum(nums));
    }

    /**
     * railgun
     * 2021/2/26 1:16
     * PS:排序 + 双指针
     */
    private List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int first = 0; first < n - 2; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = n - 1;
            int target = -nums[first];
            for (int second = first + 1; second < n - 1; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    result.add(list);
                }
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/3/2 23:23
     * PS:最接近的三数之和
     */
    @Test
    public void test16() {
        int[] nums = new int[]{-3, -2, -5, 3, -4};
        int target = -1;
        System.out.println(threeSumClosest(nums, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 10000000, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum > target) {
                    int temp = r - 1;
                    while (l < temp && nums[temp] == nums[r]) {
                        temp--;
                    }
                    r = temp;
                } else {
                    int temp = l + 1;
                    while (temp < r && nums[temp] == nums[l]) {
                        temp++;
                    }
                    l = temp;
                }
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/3/19 0:01
     * PS:电话号码的字母组合
     */
    @Test
    public void test17() {
        String digits = "23";
        GsonUtil.objectSoutJson(letterCombinations(digits));
    }

    /**
     * railgun
     * 2021/3/19 0:17
     * PS:backtrack 回溯，深度优先遍历
     */
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0){
            return new ArrayList<>();
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        List<String> combinations = new ArrayList<>();
        StringBuilder combination = new StringBuilder();
        backtrack(combinations, phoneMap, digits, 0, combination);
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuilder combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            String letters = phoneMap.get(digits.charAt(index));
            for (int i = 0; i < letters.length(); i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
    
    /**
     * railgun
     * 2021/3/20 18:54
     * PS:
     */
    @Test
    public void test18(){

    }

    /**
     * railgun
     * 2021/3/20 19:05
     * PS:暴力解法
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    /**
     * railgun
     * 2021/3/20 19:34
     * PS:删除链表的倒数第 N 个结点
     */
    @Test
    public void test19(){

    }

    /**
     * railgun
     * 2021/3/20 19:35
     * PS:双指针
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    /**
     * railgun
     * 2021/3/20 19:48
     * PS:有效的括号
     */
    @Test
    public void test20(){

    }

    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

}
