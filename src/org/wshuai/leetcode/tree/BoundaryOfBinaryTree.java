package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/07/2019.
 * #0545 https://leetcode.com/problems/boundary-of-binary-tree/
 */
public class BoundaryOfBinaryTree {

    // time O(n), space O(h)
    public List<Integer> boundaryOfBinaryTreeSingleDFS(TreeNode root) {
		// 根据题意，左边界的点必须在左子树中即 root.left != null 而右边界的点必须在
		// 右子树中 即 root.right != null 。
        List<Integer> res = new ArrayList<>();
        // 根结点的父节点同时在左右边界内
        dfs(root, true, true, res);
        return res;
    }

    private void dfs(TreeNode root, boolean leftBound, boolean rightBound, List<Integer> res) {
        // leftBound 表示当前点是否为左边界的点而 rightBound 表示当前点是否为右边界的点
        if (root == null) {
            return;
        }
        // 递的时候加入左边界的点和叶子节点
        if (leftBound) { // 加入左边界
            res.add(root.val);
        } else if (root.left == null && root.right == null) { // 加入叶子节点
            res.add(root.val);
            return;
        }
        // 左子节点在左边界内的定义延续自父节点，而左子节点在右边界内的定义为父节点不在左边界内而在右边界内且当前节点
        // 没有右子节点。
        dfs(root.left, leftBound, !leftBound && rightBound && root.right == null, res);
        // 同样的右子节点在右边界内的定义延续自父节点，而右子节点在左边界内的定义为父节点不在右边界内而在左边界内且当
        // 前节点没有左子节点。
        dfs(root.right, !rightBound && leftBound && root.left == null, rightBound, res);
        // 归的时候加入右边界的点以实现逆序 - 如果节点满足右边界的定义且没有被加入左边界
        if (!leftBound && rightBound) {
            res.add(root.val);
        }
    }

    private List<Integer> res;

    // time O(n), space O(h)
    public List<Integer> boundaryOfBinaryTreeMultiDFS(TreeNode root) {
        res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val); // 加入根结点
        leftBoundry(root.left); // 遍历左子树加入左边界内的点
        leaves(root.left); // 遍历左子树加入左子树所有的叶子节点
        leaves(root.right); // 遍历右子树加入右子树所有的叶子节点
        rightBoundry(root.right); // 遍历右子树加入右边界内的点
        return res;
    }

    private void leftBoundry(TreeNode root) {
		// 排除叶子节点
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        // 前序遍历
        res.add(root.val);
		// 有左选左，无左选右
        if (root.left == null) {
            leftBoundry(root.right);
        } else {
            leftBoundry(root.left);
        }
    }

    private void rightBoundry(TreeNode root) {
		// 排除叶子节点
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
		// 有右选右，无右选左
        if (root.right == null) {
            rightBoundry(root.left);
        } else {
            rightBoundry(root.right);
        }
        // 后序遍历以实现右边内点的逆序
        res.add(root.val);
    }

    private void leaves(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        leaves(root.left);
        leaves(root.right);
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
