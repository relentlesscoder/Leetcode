package org.wshuai.leetcode;

/**
 * Created by Wei on 02/04/2021.
 * #1740 https://leetcode.com/problems/find-distance-in-a-binary-tree/
 */
public class FindDistanceInABinaryTree {

    // time O(n), space O(n)
    public int findDistance(TreeNode root, int p, int q) {
        // 如果 p 和 q 指向同一个节点则返回 0
        if (p == q) {
            return 0;
        }
        // 找到 p 和 q 的 LCA
        TreeNode lca = lowestCommonAncestor(root, p, q);
        // 递归以 LCA 为根结点的子树，找到两个点到 LCA 的距离之和即为答案。
        // 特殊情况 如果 p 是 LCA，则返回 q 到 LCA 的距离
        if (lca.val == p) {
            return findNode(lca, q, 0);
        }
        // 特殊情况 如果 q 是 LCA，则返回 p 到 LCA 的距离
        if (lca.val == q) {
            return findNode(lca, p, 0);
        }
        // 返回p 和 q 到 LCA 的距离之和
        return findNode(lca, p, 0) + findNode(lca, q, 0);
    }

    private int findNode(TreeNode node, int val, int path) {
        if (node == null) {
            return -1;
        }
        // 找到目标节点
        if (node.val == val) {
            return path;
        }
        // 递归左子树
        int left = findNode(node.left, val, path + 1);
        if (left != -1) {
            return left;
        }
        int right = findNode(node.right, val, path + 1);
        if (right != -1) {
            return right;
        }
        return -1;
    }

    private TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
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
