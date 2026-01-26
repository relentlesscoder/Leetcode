package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/11/2019.
 * #1028 https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
 */
public class RecoverATreeFromPreorderTraversal {

    private record NodeDepth(TreeNode node, int depth) {
    }

    // time O(n), space O(n)
    public TreeNode recoverFromPreorder(String traversal) {
        int n = traversal.length(),
                val = 0, // 当前数字字符子串的值
                depth = 0; // 当前深度
        // 维护一个栈存节点和其对应的深度
        Deque<NodeDepth> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = traversal.charAt(i);
            if (c == '-') { // 当前字符等于 - 则深度增加一
                depth++;
            } else if (Character.isDigit(c)) { // 当前字符是数字
                val = val * 10 + (c - '0'); // 更新数值
                // 如果是最后一个字符或者下一个字符是 -
                if (i == n - 1 || traversal.charAt(i + 1) == '-') {
                    // 新建一个节点
                    NodeDepth nd = new NodeDepth(new TreeNode(val), depth);
                    // 将栈顶深度不为 depth - 1 的节点弹出，这一步是为了找到当前节点的父节点
                    while (!stack.isEmpty() && stack.peek().depth != depth - 1) {
                        stack.pop();
                    }
                    // 找到父节点
                    if (!stack.isEmpty()) {
                        // 先左后右
                        if (stack.peek().node.left == null) {
                            stack.peek().node.left = nd.node;
                        } else {
                            stack.peek().node.right = nd.node;
                        }
                    }
                    // 把当前节点和其深度入栈
                    stack.push(nd);
                    // 将数值和深度重置为 0
                    depth = 0;
                    val = 0;
                }
            }
        }
        return stack.peekLast().node;
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
