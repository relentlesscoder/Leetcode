package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 01/20/2016.
 * #0144 https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
public class BinaryTreePreorderTraversal {
	// time O(n), space O(1)
	public List<Integer> preorderTraversalMorris(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		TreeNode cur = root, prev = null;
		while(cur != null){
			if(cur.left == null){
				res.add(cur.val);
				cur = cur.right;
			}else{
				prev = cur.left;
				while(prev.right != null && prev.right != cur){
					prev = prev.right;
				}
				if(prev.right == null){
					prev.right = cur;
					res.add(cur.val);
					cur = cur.left;
				}else{
					prev.right = null;
					cur = cur.right;
				}
			}
		}
		return res;
	}

	// time O(n), space O(n)
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode cur = stack.pop();
			res.add(cur.val);

			if(cur.right != null){
				stack.push(cur.right);
			}
			if(cur.left != null){
				stack.push(cur.left);
			}
		}
		return res;
	}
}
