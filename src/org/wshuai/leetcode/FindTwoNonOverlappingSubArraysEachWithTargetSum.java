package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 06/20/2020.
 * #1477 https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 */
public class FindTwoNonOverlappingSubArraysEachWithTargetSum {

	// time O(n), space O(n)
	public int minSumOfLengths(int[] arr, int target) {
		int res = Integer.MAX_VALUE, sum = 0, n = arr.length;
		// save the prefix sum ending at i
		Map<Integer, Integer> map = new HashMap<>();
		// records the min length of subarray that has sum of target
		int[] dp = new int[n];
		map.put(0, -1);
		for(int i = 0; i < n; i++){
			sum += arr[i];
			dp[i] = i > 0 ? dp[i - 1] : Integer.MAX_VALUE;
			// find a prefix sum equals to (sum - target), meaning
			// subarray [j ... i] has sum target
			if(map.containsKey(sum - target)){
				int prevEnd = map.get(sum - target), len = i - prevEnd;
				// update min length found so far
				dp[i] = Math.min(dp[i], len);
				if(prevEnd != -1 && dp[prevEnd] != Integer.MAX_VALUE){
					res = Math.min(res, len + dp[prevEnd]);
				}
			}
			map.put(sum, i);
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}
}
