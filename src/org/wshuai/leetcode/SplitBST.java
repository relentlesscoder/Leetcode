package org.wshuai.leetcode;

/**
 * Created by Wei on 10/15/2019.
 * #0776 https://leetcode.com/problems/split-bst/
 */
public class SplitBST {

	// time O(n)
	// https://leetcode.com/problems/split-bst/solution/
	public TreeNode[] splitBST(TreeNode root, int V) {
		if(root == null){
			// result[0] saves nodes lte to V
			return new TreeNode[]{null, null};
		}else if(root.val <= V){
			TreeNode[] bst = splitBST(root.right, V);
			// if the current node is lte V, then it should put into
			// result[0], since the result[0] returned is from the right
			// subtree, set the right child of current node to result[0]
			root.right = bst[0];
			bst[0] = root;
			return bst;
		}else{
			// if the current node is gt V, then it should put into
			// result[1], since the result[1] returned is from the left
			// subtree, set the left child of current node to result[1]
			TreeNode[] bst = splitBST(root.left, V);
			root.left = bst[1];
			bst[1] = root;
			return bst;
		}
	}
}
