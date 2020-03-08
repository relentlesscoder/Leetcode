package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 02/15/2017.
 * #0487 https://leetcode.com/problems/max-consecutive-ones-ii/
 */
public class MaxConsecutiveOnesII {
	// time O(n), space O(1)
	// fixed length array
	public int findMaxConsecutiveOnes(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = 0, k = 1, zeros = 0;
		for (int i = 0, j = 0; j < nums.length; j++) {
			if (nums[j] == 0) {
				zeros++;
			}
			while (zeros > k) {
				if (nums[i++] == 0) {
					zeros--;
				}
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}

	// time O(n), space O(n)
	// infinite data stream
	public int findMaxConsecutiveOnesQueue(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int res = 0, n = nums.length, k = 1;
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i = 0, j = 0; j < n; j++){
			if(nums[j] == 0){
				queue.offerLast(j);
			}
			if(queue.size() > k){
				i = queue.pollFirst() + 1;
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}
}
