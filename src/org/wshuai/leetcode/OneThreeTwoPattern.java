package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/20/2019.
 * #0456 https://leetcode.com/problems/132-pattern/
 */
public class OneThreeTwoPattern {
	// time O(n), space O(n)
	public boolean find132pattern(int[] nums) {
		if(nums == null || nums.length < 3){
			return false;
		}
		Stack<Integer> stack = new Stack<>();
		int[] minFromLeft = new int[nums.length];
		minFromLeft[0] = nums[0];
		for(int i = 1; i < nums.length; i++){
			minFromLeft[i] = Math.min(minFromLeft[i - 1], nums[i]);
		}
		for(int j = nums.length - 1; j >= 0; j--){
			// check if it satisfy 1-3 part
			if(nums[j] > minFromLeft[j]){
				// crucial observation, minFromLeft[j] <= minFromLeft[i] for all i < j,
				// we can maintain a monotonic stack to store all elements (if any) we
				// found from right so far that are greater than then min.
				while(!stack.isEmpty() && stack.peek() <= minFromLeft[j]){
					stack.pop();
				}
				if(!stack.isEmpty() && stack.peek() < nums[j]){
					return true;
				}
				stack.push(nums[j]);
			}
		}
		return false;
	}
}
