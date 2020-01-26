package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/24/2016.
 * #0230 https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInABST {
	// time O(n), space O(n)
	// binary tree inorder traversal
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root, parent = null;
		while(!stack.isEmpty() || cur != null){
			if(cur != null){
				stack.push(cur);
				cur = cur.left;
			}else{
				parent = stack.pop();
				if(--k == 0){
					return parent.val;
				}
				cur = parent.right;
			}
		}
		return -1;
	}
}
