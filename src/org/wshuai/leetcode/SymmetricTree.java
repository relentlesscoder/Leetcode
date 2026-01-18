package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 10/14/2016.
 * #0101 https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {

    // time O(n), space O(h)
    public boolean isSymmetricDFS(TreeNode root) {
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return left == null;
        }
        return left.val == right.val
                && dfs(left.left, right.right)
                && dfs(left.right, right.left);
    }

    // time O(n), space O(n)
    public boolean isSymmetricBFS(TreeNode root) {
        return bfs(root.left, root.right);
    }

    private boolean bfs(TreeNode node1, TreeNode node2) {
        // 注意本题不能用 ArrayDeque 作为队列， 因为需要加入 null 到队列中。
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node1);
        queue.offer(node2);
        while (!queue.isEmpty()) {
            // 判断每一对镜像值是否相同
            TreeNode left = queue.poll(), right = queue.poll();
            // 同为空也合法
            if (left == null && right == null) {
                continue;
            }
            // 一个为空不合法
            if (left == null || right == null) {
                return false;
            }
            // 两个都不为空但值不相同也不合法
            if (left.val != right.val) {
                return false;
            }
            // 将镜像值两两入列
            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
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
