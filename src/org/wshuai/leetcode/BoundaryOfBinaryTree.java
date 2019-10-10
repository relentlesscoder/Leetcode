package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 10/7/2019.
 * #545 https://leetcode.com/problems/boundary-of-binary-tree/
 */
public class BoundaryOfBinaryTree {
	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		if(root.left == null && root.right == null){
			res.add(root.val);
			return res;
		}
		TreeNode left = root;
		List<Integer> leftBoundary = new ArrayList<>();
		while(left != null){
			leftBoundary.add(left.val);
			left = left.left == null && left != root ? left.right : left.left;
		}
		TreeNode right = root;
		List<Integer> rightBoundary = new ArrayList<>();
		while(right != null){
			rightBoundary.add(right.val);
			right = right.right == null && right != root ? right.left : right.right;
		}
		List<Integer> leaves = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;
		while(curr != null || !stack.isEmpty()){
			if(curr != null){
				stack.push(curr);
				curr = curr.left;
			}else{
				TreeNode parent = stack.pop();
				if(parent.left == null && parent.right == null){
					leaves.add(parent.val);
				}
				curr = parent.right;
			}
		}
		int leftSize = leftBoundary.size() == 1 ? 1 : leftBoundary.size() - 1;
		for(int i = 0; i < leftSize; i++){
			res.add(leftBoundary.get(i));
		}
		int leavesSize = rightBoundary.size() == 1 ? leaves.size() : leaves.size() - 1;
		for(int i = 0; i < leavesSize; i++){
			res.add(leaves.get(i));
		}
		for(int i = rightBoundary.size() - 1; i > 0; i--){
			res.add(rightBoundary.get(i));
		}
		return res;
	}
}
