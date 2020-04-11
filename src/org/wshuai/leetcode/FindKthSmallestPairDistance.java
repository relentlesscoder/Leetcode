package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/01/2020.
 * #0719 https://leetcode.com/problems/find-k-th-smallest-pair-distance/
 */
public class FindKthSmallestPairDistance {
	// time O(n^2*log(n))
	public int smallestDistancePair(int[] nums, int k) {
		Arrays.sort(nums);
		int n = nums.length, l = 0, r = nums[n - 1] - nums[0];
		while(l < r){
			int m = l + ((r - l) >> 1);
			int pairs = 0;
			// for each fixed i, count how many pairs that
			// are less than nums[i] + m
			for(int i = 0, j = 0; i < n; i++){
				while(j < n && nums[j] - nums[i] <= m){
					j++;
				}
				pairs += j - i - 1;
			}
			if(pairs < k){
				l = m + 1;
			}else{
				r = m;
			}
		}
		return l;
	}
}
