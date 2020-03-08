package org.wshuai.leetcode;

/**
 * Created by Wei on 02/12/2020.
 * #1343 https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
 */
public class NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {
	// time O(n)
	public int numOfSubarrays(int[] arr, int k, int threshold) {
		int res = 0, n = arr.length, sum = 0, target = threshold * k;
		if(n < k){
			return res;
		}
		for(int i = 0; i < n; i++){
			sum += arr[i];
			if(i >= k - 1){
				sum -= i >= k ? arr[i - k] : 0;
				if(sum >= target){
					res++;
				}
			}
		}
		return res;
	}
}
