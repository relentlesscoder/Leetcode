package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/7/19.
 * #1218 https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/
 */
public class LongestArithmeticSubsequenceOfGivenDifference {

	public int longestSubsequence(int[] arr, int difference) {
		Map<Integer, Integer> dp = new HashMap<>();
		int longest = 0;
		for(int i = 0; i < arr.length; i++){
			dp.put(arr[i], dp.getOrDefault(arr[i] - difference, 0) + 1);
			longest = Math.max(longest, dp.get(arr[i]));
		}
		return longest;
	}

	// TLE
	public int longestSubsequenceDP(int[] arr, int difference) {
		int res = 1;
		int N = arr.length;
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		for(int i = 1; i < N; i++){
			// the inner loop is inefficient since
			// we can use map to find arr[i] - difference at O(1)
			for(int j = i - 1; j >= 0; j--){
				if(arr[i] - arr[j] == difference){
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
