package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 11/10/2016.
 * #0199 https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {

    // time O(n), space O(h)
    public List<Integer> rightSideViewRecursive(TreeNode root) {
        // 二叉树递归，先遍历右子树再遍历左子树。将每一层遇到的第一个节点加入列表。
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode root, int depth, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (depth == res.size()) {
            res.add(root.val);
        }
        dfs(root.right, depth + 1, res);
        dfs(root.left, depth + 1, res);
    }

    // time O(n), space O(n)
    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 将每一层的最后(最右边)的元素加入答案列表
            res.add(queue.peekLast().val);
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
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
