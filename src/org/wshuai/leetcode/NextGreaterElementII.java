package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Wei on 02/26/2017.
 * #0503 https://leetcode.com/problems/next-greater-element-ii/
 */
public class NextGreaterElementII {

	// time O(n), space O(n)
	public int[] nextGreaterElements(int[] nums) {
		if(nums == null || nums.length == 0){
			return new int[0];
		}
		int n = nums.length, len = (n << 1);
		int[] res = new int[n];
		Stack<Integer> stack = new Stack<>();
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0, j = 0; i < len; i++){
			j = i % n;
			while(!stack.isEmpty() && nums[stack.peek()] < nums[j]){
				map.put(stack.pop(), nums[j]);
			}
			stack.push(i % n);
		}
		for(int i = 0; i < n; i++){
			res[i] = map.getOrDefault(i, -1);
		}
		return res;
	}
}
