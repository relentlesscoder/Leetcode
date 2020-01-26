package org.wshuai.leetcode;

/**
 * Created by Wei on 02/20/2017.
 * #0222 https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class CountCompleteTreeNodes {
	// time O(log(n)*log(n))
	// https://leetcode.com/problems/count-complete-tree-nodes/discuss/61953/Easy-short-c%2B%2B-recursive-solution
	public int countNodes(TreeNode root) {
		if(root == null){
			return 0;
		}
		TreeNode left = root, right = root;
		int leftHeight = 0, rightHeight = 0;
		while(left != null){
			leftHeight++;
			left = left.left;
		}
		while(right != null){
			rightHeight++;
			right = right.right;
		}
		if(leftHeight == rightHeight){
			return (1 << leftHeight) - 1;
		}
		return countNodes(root.left) + countNodes(root.right) + 1;
	}
}
