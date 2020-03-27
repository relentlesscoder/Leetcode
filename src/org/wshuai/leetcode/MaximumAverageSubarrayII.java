package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2019.
 * #0644 https://leetcode.com/problems/maximum-average-subarray-ii/
 */
public class MaximumAverageSubarrayII {
	// time O(n*log(n))
	// https://leetcode.com/problems/maximum-average-subarray-ii/discuss/105477/C%2B%2B-Clean-binary-search-solution-with-explanation
	public double findMaxAverage(int[] nums, int k) {
		double left  = Double.MAX_VALUE, right = Double.MIN_VALUE, mid;
		for(int num : nums){
			right = Math.max(right, (double)num);
			left = Math.min(left, (double)num);
		}
		while(right - left > 1e-5){
			mid = left + (right - left) / 2;
			if(isTooBig(nums, mid, k)){
				right = mid;
			}else{
				left = mid;
			}
		}
		return left;
	}

	// use prefix sum to find sum (with length k or larger) greater than 0
	private boolean isTooBig(int[] nums, double mid, int k){
		double sum = 0, prefixSum = 0, minPrefixSum = 0;
		for(int i = 0; i < nums.length; i++){
			sum += nums[i] - mid;
			if(i >= k){
				prefixSum += nums[i - k] - mid;
				// find the smallest prefix sum
				minPrefixSum = Math.min(prefixSum, minPrefixSum);
			}
			// if there exists some subarray that have larger average value, return false
			if(i >= k - 1 && sum > minPrefixSum){
				return false;
			}
		}
		return true;
	}
}
