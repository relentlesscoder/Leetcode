package org.wshuai.leetcode;

/**
 * Created by Wei on 02/01/2026.
 * #2773 https://leetcode.com/problems/height-of-special-binary-tree/
 */
public class HeightOfSpecialBinaryTree {

    // time O(n), space O(n)
    public int heightOfTree(TreeNode root) {
        boolean[] leafSet = new boolean[10_001], // 哈希集记录叶子节点
                visited = new boolean[10_001]; // 哈希集记录已经遍历过的节点
        // 寻找所有叶子节点
        findLeaves(root, leafSet, visited);
        // 计算树的高度
        return getHeight(root, leafSet) - 1;
    }

    private int getHeight(TreeNode root, boolean[] leafSet) {
        if (root == null) {
            return 0;
        }
        if (leafSet[root.val]) { // 如果是叶子节点返回 1
            return 1;
        }
        int left = getHeight(root.left, leafSet), right = getHeight(root.right, leafSet);
        return 1 + Math.max(left, right);
    }

    private void findLeaves(TreeNode root, boolean[] leafSet, boolean[] visited) {
        if (root == null) {
            return;
        }
        // 两种情况:
        //   1. 二叉树右多个叶子节点，则所有叶子节点形成一个环。如果当前节点已经被遍历过则必然是
        //   叶子节点。
        //   2. 二叉树仅有一个叶子节点，则将其加入哈希集。
        if (visited[root.val] || (root.left == null && root.right == null)) {
            leafSet[root.val] = true;
            return;
        }
        visited[root.val] = true;
        findLeaves(root.left, leafSet, visited);
        findLeaves(root.right, leafSet, visited);
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
