package com.algorithm.fourth;

import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.HashMap;
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

    /**
     * railgun
     * 2021/2/21 15:14
     * PS:搜索旋转排序数组
     */
    @Test
    public void test33() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(nums, 0));
        System.out.println(search2(nums, 0));
    }

    /**
     * railgun
     * 2021/2/21 15:25
     * PS:暴力解法
     */
    private int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * railgun
     * 2021/2/21 15:25
     * PS:二分法
     */
    public int search2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * railgun
     * 2021/2/22 22:36
     * PS:在排序数组中查找元素的第一个和最后一个位置
     */
    @Test
    public void test34() {
        int[] nums = new int[]{1};
        int target = 1;
        GsonUtil.objectSoutJson(searchRange(nums, target));
        GsonUtil.objectSoutJson(searchRange2(nums, target));
    }

    /**
     * railgun
     * 2021/2/22 22:48
     * PS:二分查找
     * 分别确定左右两边的值
     */
    private int[] searchRange(int[] nums, int target) {
        return new int[]{searchRangeChild(nums, target, true), searchRangeChild(nums, target, false)};
    }

    private int searchRangeChild(int[] nums, int target, boolean whetherLeft) {
        int result = -1;
        int mid, left = 0, right = nums.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                result = mid;
                if (whetherLeft) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/23 1:08
     * PS:二分法
     */
    public int[] searchRange2(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * railgun
     * 2021/2/26 1:19
     * PS:搜索插入位置
     */
    @Test
    public void test35() {
        int[] nums = new int[]{1, 3, 4};
        int target = 5;
        System.out.println(searchInsert(nums, target));
    }

    /**
     * railgun
     * 2021/2/28 12:00
     * PS:二分法
     */
    public int searchInsert(int[] nums, int target) {
        int result = -1, n = nums.length, left = 0, right = n - 1;
        while (left <= right) {
            int index = (left + right) / 2;
            if (target > nums[index]) {
                left = index + 1;
                result = index + 1;
            } else if (target < nums[index]) {
                right = index - 1;
                result = index;
            } else {
                result = index;
                break;
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/3/3 0:16
     * PS:有效的数独
     */
    @Test
    public void test36() {

    }

    public boolean isValidSudoku(char[][] board) {
        // init data
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer> [] columns = new HashMap[9];
        HashMap<Integer, Integer> [] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }
        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = (int)num;
                    int box_index = (i / 3 ) * 3 + j / 3;
                    // keep the current cell value
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);
                    // check if this value has been already seen before
                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1)
                        return false;
                }
            }
        }
        return true;
    }

}
