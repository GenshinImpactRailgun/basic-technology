package com.algorithm.eighth;

import com.basic.comon.util.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Author: railgun
 * 2021/2/18 22:16
 * PS:算法71-80题
 */
public class EighthDemo {

    /**
     * railgun
     * 2021/2/18 22:17
     * PS:简化路径
     */
    @Test
    public void test71() {
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }

    /**
     * railgun
     * 2021/2/18 22:21
     * PS:栈解决
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] items = path.split("/");
        for (String item : items) {
            if (item.isEmpty() || item.equals(".")) {
                continue;
            }
            if (item.equals("..")) {
                if (!stack.empty()) stack.pop();
            } else {
                stack.push(item);
            }
        }
        return "/" + String.join("/", stack);
    }

    /**
     * railgun
     * 2021/2/21 1:07
     * PS:编辑距离
     */
    @Test
    public void test72() {
        String s1 = "intention";
        String s2 = "execution";
        System.out.println(minDistance(s1, s2));
    }

    /**
     * railgun
     * 2021/2/21 1:08
     * PS:动态规划
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    /**
     * railgun
     * 2021/2/21 23:06
     * PS:矩阵置零【请使用原地算法】
     */
    @Test
    public void test73() {
        int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(matrix);
        GsonUtil.objectSoutJson(matrix);
        int[][] matrix2 = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(matrix2);
        GsonUtil.objectSoutJson(matrix2);
    }

    /**
     * railgun
     * 2021/2/21 23:21
     * PS:遍历的同时对行和列中的每个值进行赋值
     */
    public void setZeroes(int[][] matrix) {
        int MODIFIED = -1000000;
        int R = matrix.length;
        int C = matrix[0].length;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (matrix[r][c] == 0) {
                    // We modify the corresponding rows and column elements in place.
                    // Note, we only change the non zeroes to MODIFIED
                    for (int k = 0; k < C; k++) {
                        if (matrix[r][k] != 0) {
                            matrix[r][k] = MODIFIED;
                        }
                    }
                    for (int k = 0; k < R; k++) {
                        if (matrix[k][c] != 0) {
                            matrix[k][c] = MODIFIED;
                        }
                    }
                }
            }
        }
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // Make a second pass and change all MODIFIED elements to 0 """
                if (matrix[r][c] == MODIFIED) {
                    matrix[r][c] = 0;
                }
            }
        }
    }

    /**
     * railgun
     * 2021/2/21 23:20
     * PS:先标记行和列的第一个值为 0
     * 后将行列全部置为 0
     */
    public void setZeroes2(int[][] matrix) {
        Boolean isCol = false;
        int R = matrix.length;
        int C = matrix[0].length;
        for (int i = 0; i < R; i++) {
            // Since first cell for both first row and first column is the same i.e. matrix[0][0]
            // We can use an additional variable for either the first row/column.
            // For this solution we are using an additional variable for the first column
            // and using matrix[0][0] for the first row.
            if (matrix[i][0] == 0) {
                isCol = true;
            }
            for (int j = 1; j < C; j++) {
                // If an element is zero, we set the first element of the corresponding row and column to 0
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // Iterate over the array once again and using the first row and first column, update the elements.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // See if the first row needs to be set to zero as well
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }
        // See if the first column needs to be set to zero as well
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * railgun
     * 2021/2/25 0:38
     * PS:搜索二维矩阵
     */
    @Test
    public void test74() {
        int[][] nums = new int[][]{{1, 2, 3}, {4, 5, 6}};
        int target = 2;
        System.out.println(searchMatrix(nums, target));
    }

    /**
     * railgun
     * 2021/2/25 20:02
     * PS:分步骤的二分法
     */
    private boolean searchMatrix(int[][] nums, int target) {
        if (null == nums || 0 == nums.length || 0 == nums[0].length) {
            return false;
        }
        int n = nums.length, m = nums[0].length;
        int row = -1, column = -1;
        int top = 0, bottom = n - 1;
        while (top <= bottom) {
            int index = (top + bottom) >> 1;
            if (nums[index][0] > target) {
                if (0 == index) {
                    break;
                } else {
                    row = (index - 1);
                }
                bottom = index - 1;
            } else if (nums[index][0] < target) {
                row = index;
                top = index + 1;
            } else {
                row = index;
                column = 0;
                break;
            }
        }
        if (row >= 0) {
            if (nums[row][m - 1] > target) {
                int left = 1, right = m - 2;
                while (left <= right) {
                    int index = (left + right) >> 1;
                    if (nums[row][index] > target) {
                        right = index - 1;
                    } else if (nums[row][index] < target) {
                        left = index + 1;
                    } else {
                        column = index;
                        break;
                    }
                }
            } else if (nums[row][m - 1] == target) {
                column = m - 1;
            }
        }
        return row >= 0 && column >= 0;
    }

    /**
     * railgun
     * 2021/3/1 23:01
     * PS:颜色分类
     */
    @Test
    public void test75() {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(nums);
        GsonUtil.objectSoutJson(nums);
        int[] nums2 = new int[]{2, 0, 2, 1, 1, 0};
        sortColors2(nums2);
        GsonUtil.objectSoutJson(nums2);
    }

    /**
     * railgun
     * 2021/3/2 21:04
     * PS:单指针
     */
    public void sortColors(int[] nums) {
        int n = nums.length, p = 0;
        for (int i = 0; i < n; i++) {
            if (0 == nums[i]) {
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                p++;
            }
        }
        for (int i = p; i < n; i++) {
            if (1 == nums[i]) {
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                p++;
            }
        }
    }

    /**
     * railgun
     * 2021/3/2 21:04
     * PS:双指针
     */
    public void sortColors2(int[] nums) {
        int n = nums.length, p0 = 0, p1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                p1++;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                p0++;
                p1++;
            }
        }
    }

    /**
     * railgun
     * 2021/3/9 23:43
     * PS:最小覆盖子串
     */
    @Test
    public void test76() {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }

    /**
     * railgun
     * 2021/3/9 23:45
     * PS:移动窗口
     */
    public String minWindow(String s, String t) {
        return "";
    }

    /**
     * railgun
     * 2021/3/19 21:29
     * PS:
     */
    @Test
    public void test77() {
        int n = 4, k = 2;
        GsonUtil.objectSoutJson(combine(n, k));
        GsonUtil.objectSoutJson(combine2(n, k));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(result, n, k, 1, path);
        return result;
    }

    private void backtrack(List<List<Integer>> result, int n, int k, int begin, Deque<Integer> path) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
        } else {
            for (int i = begin; i <= n; i++) {
                path.addLast(i);
                backtrack(result, n, k, i + 1, path);
                path.removeLast();
            }
        }
    }

    /**
     * railgun
     * 2021/3/20 9:17
     * PS:剪枝思想
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        backtrack2(result, n, k, 1, path);
        return result;
    }

    private void backtrack2(List<List<Integer>> result, int n, int k, int begin, Deque<Integer> path) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
        } else {
            for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
                path.addLast(i);
                backtrack2(result, n, k, i + 1, path);
                path.removeLast();
            }
        }
    }

}
