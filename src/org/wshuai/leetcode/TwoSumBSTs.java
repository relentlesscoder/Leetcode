package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Wei on 10/9/2019.
 * #1214 https://leetcode.com/problems/two-sum-bsts/
 */
public class TwoSumBSTs {
	public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
		Set<Integer> set1 = inorder(root1, -1);
		Set<Integer> set2 = inorder(root2, target);
		for(int i: set1){
			if(set2.contains(i)){
				return true;
			}
		}
		return false;
	}

	private Set<Integer> inorder(TreeNode root, int target){
		Set<Integer> set = new HashSet<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;
		while(curr != null || !stack.isEmpty()){
			if(curr != null){
				stack.push(curr);
				curr = curr.left;
			}else{
				TreeNode parent = stack.pop();
				set.add(target == -1 ? parent.val : target - parent.val);
				curr = parent.right;
			}
		}
		return set;
	}
}
