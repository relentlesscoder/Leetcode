package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2019.
 * #644 https://leetcode.com/problems/maximum-average-subarray-ii/
 */
public class MaximumAverageSubarrayII {
	// explanation https://leetcode.com/problems/maximum-average-subarray-ii/discuss/105477/C%2B%2B-Clean-binary-search-solution-with-explanation
	public double findMaxAverage(int[] nums, int k) {
		double left  = Double.MAX_VALUE;
		double right = Double.MIN_VALUE;
		double mid;
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
		double sum = 0, prev_sum = 0, min_sum = 0;
		for(int i = 0; i < nums.length; i++){
			sum += nums[i] - mid;
			if(i >= k){
				prev_sum += nums[i - k] - mid;
				min_sum = Math.min(prev_sum, min_sum);
			}
			if(i >= k - 1 && sum > min_sum){
				return false;
			}
		}
		return true;
	}
}
