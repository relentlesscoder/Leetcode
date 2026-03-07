package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 02/26/2017.
 * #0513 https://leetcode.com/problems/find-bottom-left-tree-value/
 */
public class FindBottomLeftTreeValue {

	// time O(n), space O(n)
	public int findBottomLeftValueRecursive(TreeNode root) {
		// #0199 相同思路
		List<Integer> res = new ArrayList<>();
		dfs(root, 0, res);
		return res.get(res.size() - 1); // 返回最后一层的值
	}

	private void dfs(TreeNode root, int depth, List<Integer> res) {
		if (root == null) {
			return;
		}
		if (res.size() == depth) { // 将每一层第一个(最左边)节点的值加入答案列表
			res.add(root.val);
		}
		dfs(root.left, depth + 1, res);
		dfs(root.right, depth + 1, res);
	}

	// time O(n), space O(n)
	public int findBottomLeftValueBFS(TreeNode root) {
		int res = 0;
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 将每一层第一个(最左边)节点的值设为答案
			res = queue.peek().val;
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
		// 最后答案即为最深一层的第一个节点
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
