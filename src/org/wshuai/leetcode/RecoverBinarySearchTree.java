package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 04/03/2017.
 * #0099 https://leetcode.com/problems/recover-binary-search-tree/
 */
public class RecoverBinarySearchTree {
	// time O(n), space O(1)
	public void recoverTreeMorris(TreeNode root) {
		TreeNode cur = root, pred = null, prev = null, first = null, second = null;
		while(cur != null){
			if(prev != null && prev.val > cur.val){
				if(first == null){
					first = prev;
				}
				second = cur;
			}

			if(cur.left == null){
				prev = cur;
				cur = cur.right;
			}else{
				pred = cur.left;
				while(pred.right != null && pred.right != cur){
					pred = pred.right;
				}
				if(pred.right == null){
					pred.right = cur;
					cur = cur.left;
				}else{
					pred.right = null;
					prev = cur;
					cur = cur.right;
				}
			}
		}
		int temp = first.val;
		first.val = second.val;
		second.val = temp;
		return;
	}

	// time O(n), space O(n)
	public void recoverTreeStack(TreeNode root) {
		TreeNode cur = root, prev = null, first = null, second = null;
		Stack<TreeNode> stack = new Stack<>();
		while(cur != null || !stack.isEmpty()){
			if(cur == null){
				cur = stack.pop();
				if(prev != null && prev.val > cur.val){
					if(first == null){
						first = prev;
					}
					second = cur;
				}
				prev = cur;
				cur = cur.right;
			}else{
				stack.push(cur);
				cur = cur.left;
			}
		}
		int temp = first.val;
		first.val = second.val;
		second.val = temp;
		return;
	}
}
