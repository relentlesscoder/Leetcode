package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 08/19/2019.
 * #0783 https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
public class MinimumDistanceBetweenBSTNodes {

	// time O(n), space O(1)
	// morris traversal
	public int minDiffInBST(TreeNode root) {
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
						res = Math.min(res, cur.val - prev.val);
					}
					prev = cur;
					cur = cur.right;
				}
			}else{
				if(prev != null){
					res = Math.min(res, cur.val - prev.val);
				}
				prev = cur;
				cur = cur.right;
			}
		}
		return res;
	}

	// time O(n), space O(n)
	public int minDiffInBSTStack(TreeNode root) {
		int res = Integer.MAX_VALUE;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode prev = null, cur = root;
		while(!stack.isEmpty() || cur != null){
			if(cur != null){
				stack.push(cur);
				cur = cur.left;
			}else{
				TreeNode parent = stack.pop();
				if(prev != null){
					res = Math.min(res, parent.val - prev.val);
				}
				prev = parent;
				cur = parent.right;
			}
		}
		return res;
	}
}
