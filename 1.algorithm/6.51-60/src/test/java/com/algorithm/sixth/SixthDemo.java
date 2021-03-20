package com.algorithm.sixth;

import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Author: railgun
 * 2021/2/18 21:46
 * PS:算法51-60题
 */
public class SixthDemo {

    /**
     * railgun
     * 2021/2/18 21:46
     * PS:N 皇后
     */
    @Test
    public void test51() {
        GsonUtil.objectSoutJson(solveNQueens(4));
        GsonUtil.objectSoutJson(solveNQueens2(4));
    }

    /**
     * railgun
     * 2021/2/18 22:05
     * PS:基于集合的回溯
     */
    private List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    /**
     * railgun
     * 2021/2/18 22:07
     * PS:基于位运算的回溯
     */
    public List<List<String>> solveNQueens2(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(position - 1);
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                queens[row] = -1;
            }
        }
    }

    /**
     * railgun
     * 2021/2/21 0:39
     * PS:N皇后 II
     */
    @Test
    public void test52() {
        System.out.println(totalNQueens(4));
        System.out.println(totalNQueens2(4));
    }

    /**
     * railgun
     * 2021/2/21 0:41
     * PS:基于集合的回溯
     */
    public int totalNQueens(int n) {
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        return backtrack(n, 0, columns, diagonals1, diagonals2);
    }

    public int backtrack(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backtrack(n, row + 1, columns, diagonals1, diagonals2);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }

    /**
     * railgun
     * 2021/2/21 0:42
     * PS:基于位运算的回溯
     */
    public int totalNQueens2(int n) {
        return solve(n, 0, 0, 0, 0);
    }

    public int solve(int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                count += solve(n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
            }
            return count;
        }
    }

    /**
     * railgun
     * 2021/2/21 22:15
     * PS:最大子序和
     */
    @Test
    public void test53() {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray2(nums));
        System.out.println(maxSubArray3(nums));
    }

    /**
     * railgun
     * 2021/2/21 22:23
     * PS:暴力解法
     */
    private int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            result = Math.max(result, temp);
            for (int j = i + 1; j < nums.length; j++) {
                temp += nums[j];
                result = Math.max(result, temp);
            }
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/21 22:38
     * PS:动态规划
     */
    public int maxSubArray2(int[] nums) {
        int result = nums[0], temp = 0;
        for (int num : nums) {
            temp = Math.max(temp + num, num);
            result = Math.max(result, temp);
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/21 22:56
     * PS:分治
     * 类似于【线段树求解最长公共上升子序列问题】的 pushUp 操作
     */
    public int maxSubArray3(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    /**
     * railgun
     * 2021/2/23 23:26
     * PS:螺旋矩阵
     */
    @Test
    public void test54() {
        int[][] nums = new int[][]{{7}, {9}, {6}};
        GsonUtil.objectSoutJson(spiralOrder(nums));
    }

    /**
     * railgun
     * 2021/2/24 23:36
     * PS:按层模拟
     */
    private List<Integer> spiralOrder(int[][] nums) {
        List<Integer> result = new ArrayList<>();
        if (null == nums || nums.length == 0 || nums[0].length == 0) {
            return result;
        }
        int top = 0, bottom = nums.length - 1, left = 0, right = nums[0].length - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                result.add(nums[top][i]);
            }
            for (int i = top + 1; i <= bottom; i++) {
                result.add(nums[i][right]);
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i >= left; i--) {
                    result.add(nums[bottom][i]);
                }
                for (int i = bottom - 1; i >= top + 1; i--) {
                    result.add(nums[i][left]);
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return result;
    }

    /**
     * railgun
     * 2021/2/24 23:37
     * PS:模拟
     * 这个细节还挺多的
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    /**
     * railgun
     * 2021/3/1 21:24
     * PS:跳跃游戏
     */
    @Test
    public void test55() {
        int[] nums = new int[]{0};
        System.out.println(canJump(nums));
    }

    public boolean canJump(int[] nums) {
        int n = nums.length, end = 0;
        for (int i = 0; i < n; i++) {
            if (i <= end) {
                end = Math.max(end, i + nums[i]);
                if (end >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * railgun
     * 2021/3/9 22:07
     * PS:合并区间
     */
    @Test
    public void test56() {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        GsonUtil.objectSoutJson(merge(intervals));
    }

    /**
     * railgun
     * 2021/3/9 23:08
     * PS:排序
     */
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < l) {
                merged.add(new int[]{l, r});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], r);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * railgun
     * 2021/3/19 1:22
     * PS:插入区间
     */
    @Test
    public void test57() {
        int[][] intervals = new int[][]{{1, 5}};
        int[] newInterval = new int[]{2, 7};
        GsonUtil.objectSoutJson(insert(intervals, newInterval));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

}
