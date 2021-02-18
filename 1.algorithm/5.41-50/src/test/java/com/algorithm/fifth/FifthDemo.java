package com.algorithm.fifth;

import org.junit.jupiter.api.Test;

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

}
