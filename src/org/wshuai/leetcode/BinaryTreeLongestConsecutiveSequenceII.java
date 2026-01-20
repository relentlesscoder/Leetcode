package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2019.
 * #0549 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/
 */
public class BinaryTreeLongestConsecutiveSequenceII {

    private static final int MASK = (1 << 15) - 1;
    private int res = 0;

    // time O(n), space O(h)
    public int longestConsecutive(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        // dfs 用来求以当前节点为根结点的最长连续递增和递减路径的长度，因为二叉树节点数的最大值
        // 为 30000 ( 15 位整型) 我们可以把这两个状态压缩到一个整型中。低 15 位表示最长递减路
		// 径的长度而高 15 位表示最长递增路径的长度。
        if (root == null) {
            return 0;
        }
        // 递归左右子树
        int left = dfs(root.left), right = dfs(root.right);
        // 提取状态值
        int leftAsc = left >> 15, leftDesc = left & MASK;
        int rightAsc = right >> 15, rightDesc = right & MASK;
        // 左边最长递增路径的长度 (注意如果不满足递增条件则不能用 - 设长度为 0)
        int leftAscMax = root.left == null || root.left.val != root.val + 1 ? 0 : leftAsc;
        // 左边最长递减路径的长度
        int leftDescMax = root.left == null || root.left.val != root.val - 1 ? 0 : leftDesc;
        // 右边最长递增路径的长度
        int rightAscMax = root.right == null || root.right.val != root.val + 1 ? 0 : rightAsc;
        // 右边最长递减路径的长度
        int rightDescMax = root.right == null || root.right.val != root.val - 1 ? 0 : rightDesc;
        // 经过当前节点的最长连续路径为以下两者的较大值:
        //   1. 左边最长递增路径的长度 + 右边最长递减路径的长度 + 1 - 比如路径 5 <- 4 <- 3 -> 2 -> 1
        //   2. 左边最长递减路径的长度 + 右边最长递增路径的长度 + 1 - 比如路径 1 <- 2 <- 3 -> 4 -> 5
        res = Math.max(res, Math.max(leftAscMax + rightDescMax, leftDescMax + rightAscMax) + 1);
        // 返回答案:
        //   1. 低 15 位存 max(左边最长递减路径的长度, 右边最长递减路径的长度) + 1
        //   2. 高 15 位存 max(左边最长递增路径的长度, 右边最长递增路径的长度) + 1
        return ((Math.max(leftAscMax, rightAscMax) + 1) << 15)
                + Math.max(leftDescMax, rightDescMax) + 1;
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
