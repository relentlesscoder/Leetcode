package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 01/19/2016.
 * #0094 https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class BinaryTreeInorderTraversal {

    // time O(n), space O(1)
    public List<Integer> inorderTraversalMorris(TreeNode root) {
        // 将每个叶子节点的右指针与当前子树的根结点相连接形成一条辅助边
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            // 1. 当前节点不是叶子节点但是没有左子节点，继续遍历右子节点
            // 2. 当前节点为叶子节点，借助辅助边回到当前子树的根结点
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;
            } else {
                // 找到当前节点左子树的最右边的叶子节点
                TreeNode pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                // 如果是第一次遍历 (还没有辅助边)
                if (pre.right == null) {
                    pre.right = curr; // 建立辅助边
                    curr = curr.left; // 遍历左子节点
                } else { // 不是第一次遍历 (有辅助边)
                    pre.right = null; // 去掉辅助边
                    res.add(curr.val); // 中序遍历，因为已结束左子树遍历所以将当前节点加入答案
                    curr = curr.right; // 左子树遍历已结束，继续遍历右子树
                }
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode node = stack.pop();
                res.add(node.val);
                curr = node.right;
            }
        }
        return res;
    }

    // time O(n), space O(log(n))
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
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
