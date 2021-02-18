package com.algorithm.fourth;

import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

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

}
