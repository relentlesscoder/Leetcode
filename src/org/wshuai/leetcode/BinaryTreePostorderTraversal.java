package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 01/20/2016.
 * #0145 https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostorderTraversal {

	// time O(n), space O(n)
	public List<Integer> postorderTraversal(TreeNode root) {
		LinkedList<Integer> res = new LinkedList<>();
		if(root == null){
			return res;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode cur = stack.pop();
			res.offerFirst(cur.val);
			if(cur.left != null){
				stack.push(cur.left);
			}
			if(cur.right != null){
				stack.push(cur.right);
			}
		}
		return res;
	}
}
