package com.basic.comon.dataStructure;

/**
 * @Author: railgun
 * 2021/2/18 17:50
 * PS:链表数据结构
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
