package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/21/2019.
 * #0919 https://leetcode.com/problems/complete-binary-tree-inserter/
 */
public class CompleteBinaryTreeInserter {

    private static class CBTInserter {

        private TreeNode root;
        private final Deque<TreeNode> queue;

        // time O(n), space O(n)
        public CBTInserter(TreeNode root) {
            this.root = root;
            this.queue = new ArrayDeque<>();
            // BFS 将二叉树中没有子节点或者只有一个子节点的节点加入队列 queue 中
            Deque<TreeNode> temp = new ArrayDeque<>();
            temp.offer(root);
            while (!temp.isEmpty()) {
                TreeNode curr = temp.poll();
                if (curr.left == null || curr.right == null) {
                    queue.offer(curr);
                }
                if (curr.left != null) {
                    temp.offer(curr.left);
                }
                if (curr.right != null) {
                    temp.offer(curr.right);
                }
            }
        }

        // time O(1), space O(1)
        public int insert(int val) {
            // 每次将新节点设为队列最前端的节点的左或者右节点
            TreeNode newNode = new TreeNode(val);
            int res = queue.peek().val;
            if (queue.peek().left == null) {
                queue.peek().left = newNode;
            } else {
                // 如果节点已有两个子节点则从队列中移除
                queue.pop().right = newNode;
            }
            // 将新节点加入队列
            queue.offer(newNode);
            return res;
        }

        // time O(1), space O(1)
        public TreeNode get_root() {
            return this.root;
        }
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

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */
}
