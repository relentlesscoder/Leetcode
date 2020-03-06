package org.wshuai.leetcode;

/**
 * Created by Wei on 03/07/2017.
 * #0530 https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class MinimumAbsoluteDifferenceInBST {
	private TreeNode prev = null;
	private int minDiff = Integer.MAX_VALUE;

	// time O(n), space O(n)
	public int getMinimumDifference(TreeNode root) {
		inorder(root);
		return minDiff;
	}

	private void inorder(TreeNode root){
		if(root == null){
			return;
		}
		inorder(root.left);
		if(prev != null){
			minDiff = Math.min(minDiff, root.val - prev.val);
		}
		prev = root;
		inorder(root.right);
	}

	// time O(n), space O(1)
	public int getMinimumDifferenceMorris(TreeNode root) {
		int res = Integer.MAX_VALUE;
		TreeNode cur = root, prev = null;
		while(cur != null){
			if(cur.left != null){
				TreeNode pred = cur.left;
				while(pred.right != null && pred.right != cur){
					pred = pred.right;
				}
				if(pred.right == null){
					pred.right = cur;
					cur = cur.left;
				}else{
					pred.right = null;
					if(prev != null){
						res = Math.min(cur.val - prev.val, res);
					}
					prev = cur;
					cur = cur.right;
				}
			}else{
				if(prev != null){
					res = Math.min(cur.val - prev.val, res);
				}
				prev = cur;
				cur = cur.right;
			}
		}
		return res;
	}
}
