package org.wshuai.leetcode;

/**
 * Created by Wei on 8/20/19.
 * #993 https://leetcode.com/problems/cousins-in-binary-tree/solution/
 */
public class CousinsInBinaryTree {
	public boolean isCousins(TreeNode root, int x, int y) {
		int[] res = new int[4];
		search(new TreeNode(-1), root, 0, x, y, res);
		return res[0] == res[1] && res[2] != res[3];
	}

	private void search(TreeNode parent, TreeNode node, int depth, int x, int y, int[] res) {
		if (node == null) {
			return;
		}
		depth++;
		if (node.val == x) {
			res[0] = depth;
			res[2] = parent.val;
		}
		if (node.val == y) {
			res[1] = depth;
			res[3] = parent.val;
		}
		search(node, node.left, depth, x, y, res);
		search(node, node.right, depth, x, y, res);
	}
}
