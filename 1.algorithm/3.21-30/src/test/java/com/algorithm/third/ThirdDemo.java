package com.algorithm.third;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

/**
 * @Author: railgun
 * 2021/2/18 17:48
 * PS:算法21-30题
 */
public class ThirdDemo {

    /**
     * railgun
     * 2021/2/18 17:48
     * PS:合并两个有序链表
     */
    @Test
    public void test21() {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        GsonUtil.objectSoutJson(mergeTwoLists(l1, l2));
        ListNode l11 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l22 = new ListNode(1, new ListNode(3, new ListNode(4)));
        GsonUtil.objectSoutJson(mergeTwoLists2(l11, l22));
    }

    /**
     * railgun
     * 2021/2/18 18:58
     * PS:暴力解法
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(), target = result;
        while (null != l1 && null != l2) {
            if (l1.val < l2.val) {
                target.next = l1;
                l1 = l1.next;
            } else {
                target.next = l2;
                l2 = l2.next;
            }
            target = target.next;
        }
        target.next = null != l1 ? l1 : l2;
        return result.next;
    }

    /**
     * railgun
     * 2021/2/18 19:04
     * PS:递归解法
     */
    private ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

}
