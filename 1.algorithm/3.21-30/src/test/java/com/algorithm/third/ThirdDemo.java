package com.algorithm.third;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> generateParenthesis3(int n){
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
                for (String left: generate(c)) {
                    for (String right: generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

}
