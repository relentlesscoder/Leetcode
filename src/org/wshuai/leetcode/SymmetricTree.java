package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/14/2016.
 * #101 https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {
	public boolean isSymmetricRecursive(TreeNode root) {
		if (root == null) {
			return true;
		}

		return isSymmetricUtil(root.left, root.right);
	}

	private boolean isSymmetricUtil(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		return left.val == right.val
				&& isSymmetricUtil(left.right, right.left)
				&& isSymmetricUtil(left.left, right.right);
	}

	//Tree level order traversal
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		LinkedList<TreeNode> last = new LinkedList<TreeNode>();
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		last.add(root);
		while (!last.isEmpty()) {
			TreeNode node = last.remove();
			if (node != null) {
				current.add(node.left);
				current.add(node.right);
			}
			if (last.isEmpty()) {
				if (!isSymLst(current)) {
					return false;
				}
				if (!current.isEmpty()) {
					last = current;
					current = new LinkedList<TreeNode>();
				}
			}
		}
		return true;
	}

	private boolean isSymLst(LinkedList<TreeNode> nodes) {
		if (nodes.isEmpty()) {
			return true;
		}
		int len = nodes.size();
		int l = 0;
		int r = len - 1;
		while (l <= r) {
			TreeNode ln = nodes.get(l);
			TreeNode rn = nodes.get(r);
			if (ln == null && rn == null) {
			} else if (ln == null || rn == null) {
				return false;
			} else if (ln.val != rn.val) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
}
