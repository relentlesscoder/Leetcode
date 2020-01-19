package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 01/19/2016.
 * #0094 https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class BinaryTreeInorderTraversal {

	// time O(n), space O(1)
	// https://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
	public List<Integer> inorderTraversalMorris(TreeNode root) {
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
				// find predecessor
				prev = cur.left;
				while(prev.right != null && prev.right != cur){
					prev = prev.right;
				}
				if(prev.right == null){
					prev.right = cur;
					cur = cur.left;
				}else{
					prev.right = null;
					res.add(cur.val);
					cur = cur.right;
				}
			}
		}
		return res;
	}

	// time O(n), space O(n)
	public List<Integer> inorderTraversalStack(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while(cur != null || !stack.isEmpty()){
			if(cur != null){
				stack.push(cur);
				cur = cur.left;
			}else{
				TreeNode parent = stack.pop();
				res.add(parent.val);
				cur = parent.right;
			}
		}
		return res;
	}

}
