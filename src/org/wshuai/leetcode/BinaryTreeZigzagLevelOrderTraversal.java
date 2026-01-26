package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 10/01/2016.
 * #0103 https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    // time O(n), space O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
		boolean leftToRight = true; // 表示当前层的遍历方向
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> curr = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
				// 根据遍历方向决定是从队列的前面还是后面取出节点
                TreeNode node = leftToRight ? queue.poll() : queue.pollLast();
                curr.add(node.val);
                if (leftToRight) { // 如果是从左往右 - 每次从前面取出节点
					// 先左后右的将节点加入队列末尾
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                } else { // 如果是从右往左 - 每次从后面取出节点
					// 先右后左的将节点加入队列前面, 先右后左是为了保证顺序一致
                    if (node.right != null) {
                        queue.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        queue.offerFirst(node.left);
                    }
                }
            }
            res.add(curr);
            leftToRight = !leftToRight;
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
