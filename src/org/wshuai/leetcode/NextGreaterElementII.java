package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 02/26/2017.
 * #0503 https://leetcode.com/problems/next-greater-element-ii/
 */
public class NextGreaterElementII {
	// time O(n), space O(n)
	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		Stack<Integer> stack = new Stack<>();
		// first pass: to find a greater number within 0 - len
		for(int i = 0; i < n; i++){
			while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
				res[stack.pop()] = nums[i];
			}
			stack.push(i);
		}
		// second pass: to find a greater number within len - 2*len
		if(!stack.isEmpty()){
			for(int i = 0; i < n; i++){
				while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
					res[stack.pop()] = nums[i];
				}
			}
		}
		// third pass: default to -1 if we still can't find a greater number
		while(!stack.isEmpty()){
			res[stack.pop()] = -1;
		}
		return res;
	}
}
