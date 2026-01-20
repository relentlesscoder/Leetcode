package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2016.
 * #0156 https://leetcode.com/problems/binary-tree-upside-down/
 */
public class BinaryTreeUpsideDown {

    // time O(n), space O(h)
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) { // root为空或是叶子节点返回
            return root;
        }
        // 只需递归处理左子树，不需要递归右子树，右子树都是叶子节点
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        // 三角关系翻转
        root.left.left = root.right;
        root.left.right = root;
        // 根或子树的 root 变为右叶子节点
        // 注意，题解说：所有右节点，都是叶子节点，且有兄弟节点。所以，root 旋转后，都会变为右叶子节点，
        // 所以 left 和 right 设为 null 。
        root.left = null;
        root.right = null;
        return newRoot; // 同链表翻转，返回整颗树最左的叶子节点
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
