package org.wshuai.leetcode;

/**
 * Created by Wei on 10/15/2019.
 * #776 https://leetcode.com/problems/split-bst/
 */
public class SplitBST {

	// see https://leetcode.com/problems/split-bst/solution/
	public TreeNode[] splitBST(TreeNode root, int V) {
		if(root == null){
			return new TreeNode[]{null, null};
		}else if(root.val <= V){
			TreeNode[] bns = splitBST(root.right, V);
			root.right = bns[0];
			bns[0] = root;
			return bns;
		}else{
			TreeNode[] bns = splitBST(root.left, V);
			root.left = bns[1];
			bns[1] = root;
			return bns;
		}
	}
}
