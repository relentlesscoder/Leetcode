package org.wshuai.leetcode;

/**
 * Created by Wei on 02/20/2017.
 * #0333 https://leetcode.com/problems/largest-bst-subtree/
 */
public class LargestBSTSubtree {
	private int res;

	// time O(n)
	public int largestBSTSubtree(TreeNode root) {
		res = 0;
		dfs(root);
		return res;
	}

	private TreeNodeInfo dfs(TreeNode root){
		if(root == null){
			return new TreeNodeInfo(0, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
		}
		TreeNodeInfo left = dfs(root.left);
		TreeNodeInfo right = dfs(root.right);
		boolean bst = left.bst && right.bst && root.val > left.max && root.val < right.min;
		int size = bst ? 1 + left.size + right.size : 0;
		if(bst){
			res = Math.max(size, res);
		}
		int max = Math.max(Math.max(left.max, right.max), root.val);
		int min = Math.min(Math.min(left.min, right.min), root.val);
		return new TreeNodeInfo(size, max, min, bst);
	}

	private class TreeNodeInfo{
		int size;
		int max;
		int min;
		boolean bst;

		public TreeNodeInfo(int size, int max, int min, boolean bst){
			this.size = size;
			this.max = max;
			this.min = min;
			this.bst = bst;
		}
	}
}
