package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/01/2020.
 * #1602 https://leetcode.com/problems/find-nearest-right-node-in-binary-tree/
 */
public class FindNearestRightNodeInBinaryTree {

	private TreeNode res = null;

	// time O(n), space O(n)
	public TreeNode findNearestRightNodeDFS(TreeNode root, TreeNode u) {
		res = null;
		dfs(root, 0, u.val, new ArrayList<>());
		return res;
	}

	private void dfs(TreeNode root, int depth, int target, List<Integer> nums) {
		// nums 存每一层上次见到的值
		if (root == null || res != null) {
			return;
		}
		if (nums.size() == depth) { // 加入每一层第一个值
			nums.add(root.val);
		} else if (nums.get(depth) == target) { // 如果上次遇到的值等于 u 的值则找到目标节点
			res = root;
			return;
		} else {
			nums.set(depth, root.val); // 加入当前值到对应的层
		}
		dfs(root.left, depth + 1, target, nums);
		dfs(root.right, depth + 1, target, nums);
	}

	// time O(n), space O(n)
	public TreeNode findNearestRightNodeBFS(TreeNode root, TreeNode u) {
		// BFS 遍历每一层
		List<TreeNode> queue = new ArrayList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			List<TreeNode> next = new ArrayList<>();
			for (int i = 0; i < queue.size(); i++) {
				TreeNode node = queue.get(i);
				// 如果前一个值为 u 则找到目标节点
				if (i > 0 && queue.get(i - 1) == u) {
					return node;
				}
				if (node.left != null) {
					next.add(node.left);
				}
				if (node.right != null) {
					next.add(node.right);
				}
			}
			queue = next;
		}
		return null;
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
