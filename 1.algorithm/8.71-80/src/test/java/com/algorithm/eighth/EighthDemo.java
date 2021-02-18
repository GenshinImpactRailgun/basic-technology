package com.algorithm.eighth;

import org.junit.jupiter.api.Test;

import java.util.Stack;

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

}
