package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 9/5/19.
 * #1110 https://leetcode.com/problems/delete-nodes-and-return-forest/
 */
public class DeleteNodesAndReturnForest {
	private Set<Integer> nodes_to_delete = new HashSet<>();

	// delete nodes from leaves to root
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		List<TreeNode> res = new ArrayList<>();
		for (int i : to_delete) {
			nodes_to_delete.add(i);
		}
		// don't add to the list if root node is deleted
		TreeNode rem = dfs(root, res);
		if (rem != null) {
			res.add(rem);
		}
		return res;
	}

	private TreeNode dfs(TreeNode node, List<TreeNode> res) {
		if (node == null) {
			return null;
		}
		if (node.left != null) {
			node.left = dfs(node.left, res);
		}
		if (node.right != null) {
			node.right = dfs(node.right, res);
		}
		if (nodes_to_delete.contains(node.val)) {
			if (node.left != null) {
				res.add(node.left);
			}
			if (node.right != null) {
				res.add(node.right);
			}
			return null;
		}
		return node;
	}
}
