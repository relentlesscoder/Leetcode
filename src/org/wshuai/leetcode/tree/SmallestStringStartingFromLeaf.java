package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 10/28/2019.
 * #0988 https://leetcode.com/problems/smallest-string-starting-from-leaf/
 */
public class SmallestStringStartingFromLeaf {
    private String res;

    // time O(n * h), space O(h)
    public String smallestFromLeaf(TreeNode root) {
        res = "~";
        dfs(root, new StringBuilder());
        return res;
    }

    private void dfs(TreeNode root, StringBuilder curr) {
        if (root == null) {
            return;
        }
        curr.append((char) ('a' + root.val));
        // 处理叶节点，时间复杂度 O(h)
        if (root.left == null && root.right == null) {
            curr.reverse(); // 反转字符串因为我们需要从叶节点到根结点的路径
            String val = curr.toString();
            curr.reverse(); // 还原字符串
            if (val.compareTo(res) < 0) {
                res = val;
            }
        }
        // 递归左子树
        dfs(root.left, curr);
        // 递归右子树
        dfs(root.right, curr);
        // 注意递归完成后需要删除当前字符
        curr.deleteCharAt(curr.length() - 1);
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
