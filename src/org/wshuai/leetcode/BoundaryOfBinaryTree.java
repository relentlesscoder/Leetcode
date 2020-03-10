package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/07/2019.
 * #0545 https://leetcode.com/problems/boundary-of-binary-tree/
 */
public class BoundaryOfBinaryTree {
	private List<Integer> res;

	// time O(n)
	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		res = new ArrayList<>();
		if(root == null){
			return res;
		}
		res.add(root.val);
		leftBoundry(root.left);
		leaves(root.left);
		leaves(root.right);
		rightBoundry(root.right);
		return res;
	}

	private void leftBoundry(TreeNode root){
		if(root == null || (root.left == null && root.right == null)){
			return;
		}
		res.add(root.val);
		if(root.left == null){
			leftBoundry(root.right);
		}else{
			leftBoundry(root.left);
		}
	}

	private void rightBoundry(TreeNode root){
		if(root == null || (root.left == null && root.right == null)){
			return;
		}
		if(root.right == null){
			rightBoundry(root.left);
		}else{
			rightBoundry(root.right);
		}
		res.add(root.val);
	}

	private void leaves(TreeNode root){
		if(root == null){
			return;
		}
		if(root.left == null && root.right == null){
			res.add(root.val);
			return;
		}
		leaves(root.left);
		leaves(root.right);
	}
}
