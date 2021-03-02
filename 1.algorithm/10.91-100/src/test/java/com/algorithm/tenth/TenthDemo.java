package com.algorithm.tenth;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.dataStructure.TreeNode;
import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: railgun
 * 2021/2/18 22:34
 * PS:算法91-100
 */
public class TenthDemo {

    /**
     * railgun
     * 2021/2/18 22:35
     * PS:解码方法
     */
    @Test
    public void test91() {
        System.out.println(numDecodings("12"));
    }

    /**
     * railgun
     * 2021/2/18 22:42
     * PS:动态规划
     */
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1; //0个数 一种
        dp[1] = s.charAt(0) == '0' ? 0 : 1; //1个数 一种
        //dp数组中，下标都+1，表示字符个数为下标时的方法总数
        for (int i = 1; i < len; i++) {
            //10-26
            if ((s.charAt(i - 1) == '1') || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {
                //10或20
                if (s.charAt(i) == '0')
                    dp[i + 1] = dp[i - 2 + 1];

                else
                    dp[i + 1] = dp[i - 1 + 1] + dp[i - 2 + 1];
            } else {
                //比如40，50
                if (s.charAt(i) == '0')
                    return 0;

                else
                    dp[i + 1] = dp[i - 1 + 1];
            }
        }
        return dp[len];
    }

    /**
     * railgun
     * 2021/2/21 1:13
     * PS:反转链表 II
     */
    @Test
    public void test92() {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverseBetween(l1, 2, 4);
        GsonUtil.objectSoutJson(l1);
        ListNode l2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverseBetween(l2, 2, 4);
        GsonUtil.objectSoutJson(l2);
    }

    private boolean stop;
    private ListNode left;

    /**
     * railgun
     * 2021/2/21 1:15
     * PS:递归
     */
    public void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    /**
     * railgun
     * 2021/2/21 1:19
     * PS:迭代链接反转
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }

    /**
     * railgun
     * 2021/2/21 23:47
     * PS:复原 IP 地址
     */
    @Test
    public void test93() {
        String s = "25525511135";
        GsonUtil.objectSoutJson(restoreIpAddresses(s));
    }

    /**
     * railgun
     * 2021/2/21 23:48
     * PS:回溯
     */
    public List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<>();
    int[] segments = new int[SEG_COUNT];

    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }

    /**
     * railgun
     * 2021/2/25 1:20
     * PS:二叉树的中序遍历
     */
    @Test
    public void test94() {
        TreeNode t = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(6), null));
        GsonUtil.objectSoutJson(inorderTraversal(t));
        TreeNode t2 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(6), null));
        GsonUtil.objectSoutJson(inorderTraversal2(t2));
    }

    /**
     * railgun
     * 2021/2/25 21:35
     * PS:递归
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode treeNode, List<Integer> result) {
        if (null == treeNode) {
            return;
        }
        inorder(treeNode.left, result);
        result.add(treeNode.val);
        inorder(treeNode.right, result);
    }

    /**
     * railgun
     * 2021/2/25 22:07
     * PS:栈
     */
    private List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        while (null != root || !deque.isEmpty()) {
            while (null != root) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }
    
    /**
     * railgun
     * 2021/3/2 22:15
     * PS:不同的二叉搜索树Ⅱ
     */
    @Test
    public void test95(){
        int n = 3;
        GsonUtil.objectSoutJson(generateTrees(n));
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

}
