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

}
