package com.algorithm.first;

import com.basic.comon.dataStructure.ListNode;
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

}
