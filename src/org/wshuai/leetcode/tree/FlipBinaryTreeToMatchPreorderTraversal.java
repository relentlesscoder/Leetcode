package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2019.
 * #0971 https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/
 */
public class FlipBinaryTreeToMatchPreorderTraversal {
    private int idx; // 全局变量表示前序遍历到的位置

    // time O(n), space O(h)
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        idx = 0;
        List<Integer> res = new ArrayList<>();
        return dfs(root, voyage, res) ? res : List.of(-1);
    }

    private boolean dfs(TreeNode node, int[] voyage, List<Integer> res) {
        if (node == null) {
            return true;
        }
        // 值不同则不合法
        if (voyage[idx] != node.val) {
            return false;
        }
        idx++;
        // 如果下一个位置与 voyage 中的值不同则翻砖一次
        if (node.left != null && node.left.val != voyage[idx]) {
            res.add(node.val);
            // 先遍历右再遍历左 - 利用改变遍历顺序来模拟翻转
            return dfs(node.right, voyage, res) && dfs(node.left, voyage, res);
        }
        // 先遍历左再遍历右
        return dfs(node.left, voyage, res) && dfs(node.right, voyage, res);
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
