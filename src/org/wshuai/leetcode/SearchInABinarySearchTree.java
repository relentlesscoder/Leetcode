package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2019.
 * #0700 https://leetcode.com/problems/search-in-a-binary-search-tree/
 */
public class SearchInABinarySearchTree {
	// time O(log(n))
	public TreeNode searchBST(TreeNode root, int val) {
		if(root == null){
			return null;
		}
		if(root.val == val){
			return root;
		}
		if(root.val > val){
			return searchBST(root.left, val);
		}else{
			return searchBST(root.right, val);
		}
	}
}
