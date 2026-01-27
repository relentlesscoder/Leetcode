package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 10/20/2019.
 * #0662 https://leetcode.com/problems/maximum-width-of-binary-tree/
 */
public class MaximumWidthOfBinaryTree {

    // time O(n), space O(n)
    public int widthOfBinaryTreeDFS(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
    }

    private int dfs(TreeNode root, int level, int index, List<Integer> start, List<Integer> end) {
        if (root == null) {
            return 0;
        }
        // 把第一个横向索引加入到层开始和结束列表中
        if (start.size() == level) {
            start.add(index);
            end.add(index);
        } else {
            // 将后面每个横向索引加入到结束列表中
            end.set(level, index);
        }
        int cur = end.get(level) - start.get(level) + 1;
        int left = dfs(root.left, level + 1, 2 * index, start, end);
        int right = dfs(root.right, level + 1, 2 * index + 1, start, end);
        return Math.max(cur, Math.max(left, right));
    }

    private record NodeWidth(TreeNode node, int width) {
    }

	// time O(n), space O(n)
    public int widthOfBinaryTreeBFS(TreeNode root) {
        int res = 0;
        Deque<NodeWidth> queue = new ArrayDeque<>();
        queue.offer(new NodeWidth(root, 0));
        while (!queue.isEmpty()) {
            int size = queue.size(), left = queue.peek().width;
            while (size-- > 0) {
                NodeWidth curr = queue.poll();
                res = Math.max(res, curr.width - left + 1);
                if (curr.node.left != null) {
                    queue.offer(new NodeWidth(curr.node.left, curr.width * 2));
                }
                if (curr.node.right != null) {
                    queue.offer(new NodeWidth(curr.node.right, curr.width * 2 + 1));
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
