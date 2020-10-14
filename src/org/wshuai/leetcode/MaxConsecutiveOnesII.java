package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 02/15/2017.
 * #0487 https://leetcode.com/problems/max-consecutive-ones-ii/
 */
public class MaxConsecutiveOnesII {

	// time O(n), space O(1)
	public int findMaxConsecutiveOnes(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = 0, n = nums.length;
		for(int i = 0, j = 0, zeros = 0; j < n; j++){
			zeros += nums[j] == 0 ? 1 : 0;
			while(zeros > 1){
				zeros -= nums[i++] == 0 ? 1 : 0;
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}

	// time O(n), space O(n)
	public int findMaxConsecutiveOnesCountLeftAndRight(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = 0, n = nums.length, count = 0;
		int[] onesFromLeft = new int[n];
		for(int i = 1; i < n; i++){
			count = nums[i - 1] == 1 ? count + 1 : 0;
			onesFromLeft[i] = count;
		}
		count = 0;
		for(int i = n - 1; i >= 0; i--){
			res = Math.max(res, onesFromLeft[i] + count + 1);
			count = nums[i] == 0 ? 0 : count + 1;
		}
		return res;
	}

	// time O(n), space O(n)
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
