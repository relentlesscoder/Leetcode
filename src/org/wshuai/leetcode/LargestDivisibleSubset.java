package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0368 https://leetcode.com/problems/largest-divisible-subset/
 */
public class LargestDivisibleSubset {
	// time O(n^2), space O(n)
	public List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if(nums == null || nums.length == 0){
			return res;
		}
		int n = nums.length, largest = 0, largestIndex = -1;
		int[] dp = new int[n], index = new int[n];
		Arrays.fill(dp, 1);
		Arrays.fill(index, -1);
		Arrays.sort(nums);
		for(int i = 0; i < n; i++){
			for(int j = i - 1; j >= 0; j--){
				if(nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]){
					dp[i] = dp[j] + 1;
					index[i] = j;
				}
			}
			if(dp[i] > largest){
				largest = dp[i];
				largestIndex = i;
			}
		}
		for(int i = largestIndex; i != -1; i = index[i]){
			res.add(nums[i]);
		}
		return res;
	}
}
