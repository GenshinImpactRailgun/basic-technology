package com.algorithm.first;

import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

}
