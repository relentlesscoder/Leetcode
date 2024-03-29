package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 12/07/2020.
 * #1676 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
 */
public class LowestCommonAncestorOfABinaryTreeIV {

	// time O(n), space O(n)
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
		Set<TreeNode> set = new HashSet<>();
		for (TreeNode node : nodes) {
			set.add(node);
		}
		return dfs(root, set);
	}

	private TreeNode dfs(TreeNode root, Set<TreeNode> nodes) {
		if (root == null || nodes.contains(root)) { // pop two nodes up until LCA is found
			return root;
		}
		TreeNode left = dfs(root.left, nodes);
		TreeNode right = dfs(root.right, nodes);
		if (left == null) {
			return right;
		} else if (right == null) {
			return left;
		}
		return root;
	}

	// time O(n*log(d))
	public TreeNode lowestCommonAncestorDivideConquer(TreeNode root, TreeNode[] nodes) {
		return merge(root, nodes, 0, nodes.length - 1);
	}

	private TreeNode merge(TreeNode root, TreeNode[] nodes, int i, int j) {
		if (i == j) {
			return nodes[i];
		}
		int k = i + (j - i) / 2;
		TreeNode left = merge(root, nodes, i, k);
		TreeNode right = merge(root, nodes, k + 1, j);
		return helper(root, left, right);
	}

	public TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}
		TreeNode left = helper(root.left, p, q);
		TreeNode right = helper(root.right, p, q);
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		return root;
	}
}
