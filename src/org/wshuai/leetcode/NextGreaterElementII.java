package org.wshuai.leetcode;

import java.util.Arrays;
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
		int n = nums.length, len = n << 1;
		int[] res = new int[n];
		Arrays.fill(res, -1);
		Stack<Integer> queue = new Stack<>();
		for(int i = 0; i < len; i++){
			int cur = nums[i % n];
			while(!queue.isEmpty() && cur > nums[queue.peek()]){
				res[queue.pop()] = cur;
			}
			queue.push(i % n);
		}
		return res;
	}
}
