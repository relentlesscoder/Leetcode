package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 11/19/2016.
 * #0255 https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
 */
public class VerifyPreorderSequenceInBinarySearchTree {
	// time O(n), space O(n)
	public boolean verifyPreorder(int[] preorder) {
		int min = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<>();
		for(int p : preorder){
			if(p < min){
				return false;
			}
			// pop out all the left tree nodes if we see right tree node
			while(!stack.isEmpty() && p > stack.peek()){
				min = stack.pop();
			}
			// push the left subtree nodes
			stack.push(p);
		}
		return true;
	}
}
