package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 03/16/2020.
 * #1382 https://leetcode.com/problems/balance-a-binary-search-tree/
 */
public class BalanceABinarySearchTree {

    // time O(n), space O(n)
    public TreeNode balanceBST(TreeNode root) {
		// 先中序遍历二叉树的到排序后的节点数组，然后递归数组每次选中间节点为当前子树的
		// 根结点以构造平衡二叉树。
        List<TreeNode> nodes = new ArrayList<>();
		// 中序遍历
        inorder(root, nodes);
		// 构造二叉树
        return build(nodes, 0, nodes.size() - 1);
    }

    private TreeNode build(List<TreeNode> nodes, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = nodes.get(mid);
        root.left = build(nodes, start, mid - 1);
        root.right = build(nodes, mid + 1, end);
        return root;
    }

    private void inorder(TreeNode root, List<TreeNode> nodes) {
        if (root == null) {
            return;
        }
        inorder(root.left, nodes);
        nodes.add(root);
        inorder(root.right, nodes);
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
