package com.algorithm.ninth;

import org.junit.jupiter.api.Test;

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

}
