package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 9/24/2016.
 */
public class KthSmallestElementInABST {
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> stk = new Stack<TreeNode>();

		TreeNode n = root;
		int result = -1;

		while (!stk.isEmpty() || n != null) {
			if (n != null) {
				stk.push(n);
				n = n.left;
			} else {
				TreeNode t = stk.pop();
				k--;
				if (k == 0) {
					result = t.val;
					break;
				}
				n = t.right;
			}
		}

		return result;
	}
}
