package org.wshuai.leetcode;

/**
 * Created by Wei on 10/28/2019.
 * #1080 https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/
 */
public class InsufficientNodesInRootToLeafPaths {
	public TreeNode sufficientSubset(TreeNode root, int limit) {
		boolean val = dfs(root, limit, 0);
		return val ? null : root;
	}

	private boolean dfs(TreeNode node, int limit, int sum){
		if(node.left == null && node.right == null){
			return sum + node.val < limit;
		}
		if(node.left == null){
			boolean right = dfs(node.right, limit, sum + node.val);
			if(right){
				node.right = null;
			}
			return right;
		}
		if(node.right == null){
			boolean left = dfs(node.left, limit, sum + node.val);
			if(left){
				node.left = null;
			}
			return left;
		}
		boolean left = dfs(node.left, limit, sum + node.val);
		boolean right = dfs(node.right, limit, sum + node.val);
		if(left){
			node.left = null;
		}
		if(right){
			node.right = null;
		}
		return left && right;
	}
}
