package org.wshuai.leetcode;

/**
 * Created by Wei on 10/20/2019.
 * #0510 https://leetcode.com/problems/inorder-successor-in-bst-ii/
 */
public class InorderSuccessorInBSTII {
	// time O(log(n))
	public TreeNodeWithParent inorderSuccessor(TreeNodeWithParent node) {
		if(node.right != null){
			TreeNodeWithParent successor = node.right;
			while(successor != null && successor.left != null){
				successor = successor.left;
			}
			return successor;
		}else{
			TreeNodeWithParent successor = node.parent;
			while (successor != null && successor.val < node.val) {
				successor = successor.parent;
			}
			return successor;
		}
	}

	private class TreeNodeWithParent{
		public int val;

		public TreeNodeWithParent left;

		public TreeNodeWithParent right;

		public TreeNodeWithParent parent;

	}
}
