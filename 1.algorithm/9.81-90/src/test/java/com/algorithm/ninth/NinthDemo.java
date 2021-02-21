package com.algorithm.ninth;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
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

    /**
     * railgun
     * 2021/2/21 1:10
     * PS:删除排序链表中的重复元素 II
     */
    @Test
    public void test82() {
        GsonUtil.objectSoutJson(deleteDuplicates(new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3)))))));
    }

    /**
     * railgun
     * 2021/2/21 1:12
     * PS:熟悉链表操作
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        ListNode cur = prev.next;
        while (cur != null) {
            int curRepeatNum = 0;
            ListNode difNode = cur;
            // 找到和cur指向的结点值不同的结点
            while (difNode != null && difNode.val == cur.val) {
                curRepeatNum++;
                difNode = difNode.next;
            }
            // 如果curRepeatNum的值大于1，则表示cur指向的结点重复出现了
            if (curRepeatNum > 1) {
                prev.next = difNode;
            } else {
                // cur指向的结点没有重复出现，则将变量prev指向cur所指向的结点
                prev = cur;
            }
            cur = difNode;
        }
        return dummyHead.next;
    }

    /**
     * railgun
     * 2021/2/21 23:25
     * PS:
     */
    @Test
    public void test83() {
        ListNode l = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        GsonUtil.objectSoutJson(deleteDuplicates83(l));
        ListNode l2 = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        GsonUtil.objectSoutJson(deleteDuplicates832(l2));
    }

    /**
     * railgun
     * 2021/2/21 23:37
     * PS:哑结点
     */
    private ListNode deleteDuplicates83(ListNode l) {
        ListNode result = new ListNode(Integer.MAX_VALUE), target = result;
        while (null != l) {
            if (target.val != l.val) {
                target.next = new ListNode(l.val);
                target = target.next;
            }
            l = l.next;
        }
        return result.next;
    }

    /**
     * railgun
     * 2021/2/21 23:37
     * PS:直接操作链表对象【速度比哑结点方法快】
     */
    private ListNode deleteDuplicates832(ListNode l) {
        ListNode result = l;
        while (null != l && null != l.next) {
            if (l.val == l.next.val) {
                l.next = l.next.next;
            } else {
                l = l.next;
            }
        }
        return result;
    }

}
