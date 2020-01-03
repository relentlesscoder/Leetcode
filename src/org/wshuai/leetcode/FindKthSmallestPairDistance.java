package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 1/1/2020.
 * #719 https://leetcode.com/problems/find-k-th-smallest-pair-distance/
 */
public class FindKthSmallestPairDistance {
	public int smallestDistancePair(int[] nums, int k) {
		Arrays.sort(nums);

		int n = nums.length;
		int l = 0;
		int r = nums[n - 1] - nums[0];

		while(l < r){
			int m = l + (r - l) / 2;
			int cnt = 0;
			// for each fixed i, count how many pairs that are less than nums[i] + m
			for(int i = 0, j = 0; i < n; i++){
				while(j < n && nums[j] <= nums[i] + m){
					j++;
				}
				cnt += j - i - 1;
			}
			if(cnt < k){
				l = m + 1;
			}else{
				r = m;
			}
		}
		return l;
	}
}
