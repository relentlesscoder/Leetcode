package org.wshuai.leetcode;

/**
 * Created by Wei on 7/25/2017.
 * #572 https://leetcode.com/problems/subtree-of-another-tree/
 */
public class SubtreeOfAnotherTree {

	//59ms
	public boolean isSubtreeRecursive(TreeNode s, TreeNode t) {
		String st = preOrderTraversal(s);
		String tt = preOrderTraversal(t);
		return st.contains(tt);
	}

	private String preOrderTraversal(TreeNode node) {
		String left = node.left == null ? "lnull" : preOrderTraversal(node.left);
		String right = node.right == null ? "rnull" : preOrderTraversal(node.right);
		return "#" + Integer.toString(node.val) + left + right;
	}
}
