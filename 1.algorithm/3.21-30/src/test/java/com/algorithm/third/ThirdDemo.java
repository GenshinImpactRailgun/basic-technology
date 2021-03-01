package com.algorithm.third;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    /**
     * railgun
     * 2021/2/20 19:00
     * PS:括号生成
     */
    @Test
    public void test22() {
        GsonUtil.objectSoutJson(generateParenthesis(3));
        GsonUtil.objectSoutJson(generateParenthesis2(3));
        GsonUtil.objectSoutJson(generateParenthesis3(3));
    }

    /**
     * railgun
     * 2021/2/20 19:02
     * PS:暴力解法
     */
    private List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateAll(new char[2 * n], 0, result);
        return result;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return 0 == balance;
    }

    /**
     * railgun
     * 2021/2/21 0:01
     * PS:回溯法
     */
    private List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * railgun
     * 2021/2/21 0:06
     * PS:按括号序列的长度递归
     */
    private List<String> generateParenthesis3(int n) {
        return generate(n);
    }

    private ArrayList[] cache = new ArrayList[100];

    public List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left : generate(c)) {
                    for (String right : generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

    /**
     * railgun
     * 2021/2/21 13:05
     * PS:合并 k 个升序链表
     */
    @Test
    public void test23() {
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));
        GsonUtil.objectSoutJson(mergeKLists(l1, l2, l3));
        ListNode l21 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l22 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l23 = new ListNode(2, new ListNode(6));
        ListNode[] array2 = new ListNode[]{l21, l22, l23};
        GsonUtil.objectSoutJson(mergeKLists2(array2));
        ListNode l31 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l32 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l33 = new ListNode(2, new ListNode(6));
        ListNode[] array3 = new ListNode[]{l31, l32, l33};
        GsonUtil.objectSoutJson(mergeKLists3(array3));
        ListNode l41 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l42 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l43 = new ListNode(2, new ListNode(6));
        ListNode[] array4 = new ListNode[]{l41, l42, l43};
        GsonUtil.objectSoutJson(mergeKLists4(array4));
    }

    /**
     * railgun
     * 2021/2/21 14:20
     * PS:一次取一个值的想法，很差
     */
    private ListNode mergeKLists(ListNode... l) {
        ListNode result = new ListNode(-1), target = result;
        addNode(target, l);
        return result.next;
    }

    private void addNode(ListNode target, ListNode... l) {
        int n = -1, nodeVal = Integer.MAX_VALUE;
        for (int i = 0; i < l.length; i++) {
            if (null != l[i] && l[i].val < nodeVal) {
                nodeVal = l[i].val;
                n = i;
            }
        }
        if (n >= 0) {
            target.next = new ListNode(l[n].val);
            target = target.next;
            l[n] = l[n].next;
            boolean arrayEmpty = true;
            for (ListNode listNode : l) {
                if (null != listNode) {
                    arrayEmpty = false;
                }
            }
            if (!arrayEmpty) {
                addNode(target, l);
            }
        }
    }

    /**
     * railgun
     * 2021/2/21 14:22
     * PS:顺序合并
     */
    private ListNode mergeKLists2(ListNode[] list) {
        ListNode result = null;
        for (int i = 0; i < list.length; i++) {
            result = mergeTwoLists32(result, list[i]);
        }
        return result;
    }

    private ListNode mergeTwoLists32(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1), target = result;
        while (null != l1 || null != l2) {
            if (null == l1) {
                target.next = l2;
                break;
            }
            if (null == l2) {
                target.next = l1;
                break;
            }
            if (l1.val < l2.val) {
                target.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                target.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            target = target.next;
        }
        return result.next;
    }

    /**
     * railgun
     * 2021/2/21 14:54
     * PS:分治合并
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists3(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists3(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    /**
     * railgun
     * 2021/2/21 15:03
     * PS:使用优先队列合并
     */
    private ListNode mergeKLists4(ListNode[] lists) {
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

    PriorityQueue<Status> queue = new PriorityQueue<>();

    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        @Override
        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    /**
     * railgun
     * 2021/2/22 22:07
     * PS:两两交换链表中的节点
     */
    @Test
    public void test24() {
        ListNode l = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        GsonUtil.objectSoutJson(swapPairs(l));
        GsonUtil.objectSoutJson(swapPairs2(l));
    }

    /**
     * railgun
     * 2021/2/22 22:20
     * PS:判断是否有两个连续的节点提供交换的条件
     * 迭代
     */
    private ListNode swapPairs(ListNode l) {
        ListNode result = new ListNode(Integer.MAX_VALUE), target = result;
        while (null != l) {
            if (null == l.next) {
                target.next = new ListNode(l.val);
                break;
            } else {
                target.next = new ListNode(l.next.val);
                target = target.next;
                target.next = new ListNode(l.val);
                target = target.next;
                l = l.next.next;
            }
        }
        return result.next;
    }

    /**
     * railgun
     * 2021/2/22 22:21
     * PS:递归
     */
    private ListNode swapPairs2(ListNode l) {
        if (null == l || null == l.next) {
            return l;
        }
        ListNode newL = l.next;
        l.next = swapPairs2(newL.next);
        newL.next = l;
        return newL;
    }

    /**
     * railgun
     * 2021/2/26 1:17
     * PS:K 个一组翻转链表
     */
    @Test
    public void test25() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k = 2;
        GsonUtil.objectSoutJson(reverseKGroup(head, k));
    }

    /**
     * railgun
     * 2021/2/27 19:17
     * PS:引入头部之前节点 hair ，用来更方便的解决问题
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode();
        hair.next = head;
        ListNode prev = hair;
        while (head != null) {
            ListNode tail = prev;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] listNodes = myReverse(head, tail);
            ListNode newHead = listNodes[0];
            ListNode newTail = listNodes[1];
            newTail.next = next;
            prev.next = newHead;
            prev = newTail;
            head = prev.next;
        }
        return hair.next;
    }

    /**
     * railgun
     * 2021/2/27 23:29
     * PS:反转子链表
     */
    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }
        return new ListNode[]{tail, head};
    }

}
