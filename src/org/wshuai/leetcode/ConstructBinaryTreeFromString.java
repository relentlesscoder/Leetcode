package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/09/2019.
 * #0536 https://leetcode.com/problems/construct-binary-tree-from-string/
 */
public class ConstructBinaryTreeFromString {

    // time O(n), space O(n)
    public TreeNode str2tree(String s) {
        int n = s.length(),
				val = 0, // 当前数字字符串表示的数值
				sign = 0; // 当前数值的符号 - 0 为正，1 为负
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') { // 当前字符为数字，更新数值
                val = val * 10 + (c - '0');
            } else if (c == '-') { // 当前字符为负号，将 sign 设为负
                sign = 1;
            } else { // 当前字符为左括号或右括号
                if (i > 0 && Character.isDigit(s.charAt(i - 1))) {
                    val = sign == 1 ? -val : val; // 根据 sign 的值给数值添加符号
                    stack.push(new TreeNode(val)); // 入栈
                    val = 0; // 重置 val
                    sign = 0; // 重置 sign
                }
                if (c == ')') { // 如果为右括号
                    TreeNode child = stack.pop(); // 出栈
                    if (stack.peek().left == null) { // 将当前节点设为父节点的左或者右子节点
                        stack.peek().left = child;
                    } else {
                        stack.peek().right = child;
                    }
                }
            }
        }
		// 特殊情况: 字符串只含有一个数
        if (val > 0) {
            stack.push(new TreeNode(sign == 1 ? -val : val));
        }
        return stack.isEmpty() ? null : stack.peek();
    }

	/**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
