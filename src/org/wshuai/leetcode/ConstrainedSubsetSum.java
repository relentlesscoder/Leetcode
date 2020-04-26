package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 04/26/2020.
 * #1425 https://leetcode.com/problems/constrained-subset-sum/
 */
public class ConstrainedSubsetSum {
	// time O(n), space O(n)
	public int constrainedSubsetSum(int[] nums, int k) {
		int n = nums.length, res = Integer.MIN_VALUE;
		// dp[i] denotes the max sum subsequence ends at i
		// dp[i] = Math.max(nums[i] + Math.max(dp[i - 1], dp[i - 2], ... dp[i - k]))
		int[] dp = new int[n];
		// monotonic decreasing queue
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i = 0; i < n; i++){
			// get the max within the current window
			int firstIndex = queue.isEmpty() ? -1 : queue.peekFirst();
			int prevMax = firstIndex == -1 ? 0 : dp[firstIndex];
			dp[i] = prevMax + nums[i];
			res = Math.max(res, dp[i]);
			// remove the head out of the window
			if(firstIndex <= i - k){
				queue.pollFirst();
			}
			// maintain monotonic queue
			while(!queue.isEmpty() && dp[i] >= dp[queue.peekLast()]){
				queue.pollLast();
			}
			// only push positive result since only positive
			// sum will help future calculation
			if(dp[i] > 0){
				queue.offerLast(i);
			}
		}
		return res;
	}
}
