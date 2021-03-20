package com.algorithm.seventh;

import com.basic.comon.dataStructure.ListNode;
import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: railgun
 * 2021/2/18 22:10
 * PS:算法61-70题
 */
public class SeventhDemo {

    /**
     * railgun
     * 2021/2/18 22:10
     * PS:旋转链表
     */
    @Test
    public void test61() {
        ListNode l = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        GsonUtil.objectSoutJson(rotateRight(l, 4));
    }

    /**
     * railgun
     * 2021/2/18 22:14
     * PS:先将链表闭合成环
     * 找到相应的位置断开这个环，确定新的链表头和链表尾
     */
    public ListNode rotateRight(ListNode head, int k) {
        // base cases
        if (head == null) return null;
        if (head.next == null) return head;

        // close the linked list into the ring
        ListNode old_tail = head;
        int n;
        for (n = 1; old_tail.next != null; n++)
            old_tail = old_tail.next;
        old_tail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        ListNode new_tail = head;
        for (int i = 0; i < n - k % n - 1; i++)
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;

        return new_head;
    }

    /**
     * railgun
     * 2021/2/21 1:03
     * PS:不同路径
     */
    @Test
    public void test62() {
        System.out.println(uniquePaths(3, 7));
        System.out.println(uniquePaths2(3, 7));
    }

    /**
     * railgun
     * 2021/2/21 1:03
     * PS:动态规划
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    /**
     * railgun
     * 2021/2/21 1:04
     * PS:组合数学
     */
    public int uniquePaths2(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

    /**
     * railgun
     * 2021/2/21 22:58
     * PS:不同路径 Ⅱ
     */
    @Test
    public void test63() {
        int[][] obstacleGrid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    /**
     * railgun
     * 2021/2/21 23:04
     * PS:动态规划
     */
    private int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int[] f = new int[m];
        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int[] ints : obstacleGrid) {
            for (int j = 0; j < m; ++j) {
                if (ints[j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && ints[j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[m - 1];
    }

    /**
     * railgun
     * 2021/2/24 23:54
     * PS:最小路径和
     */
    @Test
    public void test64() {
        int[][] nums = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum(nums));
    }

    /**
     * railgun
     * 2021/2/25 0:22
     * PS:动态规划
     */
    private int minPathSum(int[][] nums) {
        if (null == nums || 0 == nums.length || 0 == nums[0].length) {
            return 0;
        }
        int n = nums.length, m = nums[0].length;
        int[][] result = new int[n][m];
        result[0][0] = nums[0][0];
        for (int i = 1; i < n; i++) {
            result[i][0] = nums[i][0] + result[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            result[0][i] = nums[0][i] + result[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                result[i][j] = nums[i][j] + Math.min(result[i - 1][j], result[i][j - 1]);
            }
        }
        return result[n - 1][m - 1];
    }

    /**
     * railgun
     * 2021/3/1 21:54
     * PS:有效数字
     */
    @Test
    public void test65() {
        String s = "4.4E-4";
        System.out.println(isNumber(s));
    }

    /**
     * railgun
     * 2021/3/1 22:45
     * PS:有限状态机
     */
    public boolean isNumber(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<State, Map<CharType, State>>();
        Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
        }};
        transfer.put(State.STATE_INITIAL, initialMap);
        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INT_SIGN, intSignMap);
        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
        }};
        transfer.put(State.STATE_INTEGER, integerMap);
        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
        }};
        transfer.put(State.STATE_POINT, pointMap);
        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);
        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);
        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);
        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);

        int length = s.length();
        State state = State.STATE_INITIAL;

        for (int i = 0; i < length; i++) {
            CharType type = toCharType(s.charAt(i));
            if (!transfer.get(state).containsKey(type)) {
                return false;
            } else {
                state = transfer.get(state).get(type);
            }
        }
        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }

    public CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    enum State {
        STATE_INITIAL,
        STATE_INT_SIGN,
        STATE_INTEGER,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,
        STATE_END,
    }

    enum CharType {
        CHAR_NUMBER,
        CHAR_EXP,
        CHAR_POINT,
        CHAR_SIGN,
        CHAR_ILLEGAL,
    }

    /**
     * railgun
     * 2021/3/9 23:09
     * PS:加一
     */
    @Test
    public void test66() {
        int[] digits = new int[]{9, 9, 9};
        GsonUtil.objectSoutJson(plusOne(digits));
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] + carry == 10) {
                digits[i] = 0;
                carry = 1;
            } else {
                digits[i] += carry;
                carry = 0;
            }
        }
        if (carry == 1) {
            int[] result = new int[n + 1];
            result[0] = 1;
            return result;
        }
        return digits;
    }

    /**
     * railgun
     * 2021/3/19 21:02
     * PS:二进制求和
     */
    @Test
    public void test67() {
        String a = "1010", b = "1011";
        System.out.println(addBinary(a, b));
    }

    /**
     * railgun
     * 2021/3/19 21:26
     * PS:模拟
     */
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; i++) {
            carry += i < a.length() ? a.charAt(a.length() - i - 1) - '0' : 0;
            carry += i < b.length() ? b.charAt(b.length() - i - 1) - '0' : 0;
            result.append(carry % 2);
            carry /= 2;
        }
        if (carry > 0) {
            result.append(1);
        }
        result.reverse();
        return result.toString();
    }

}
