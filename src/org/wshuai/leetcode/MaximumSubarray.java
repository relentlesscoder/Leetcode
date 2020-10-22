package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2016.
 * #0053 https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {

	// time O(n)
	// Kadane's algorithm
	// https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d
	public int maxSubArray(int[] nums) {
		int res = nums[0];
		for(int i = 1; i < nums.length; i++){
			if(nums[i - 1] > 0){
				nums[i] += nums[i - 1];
			}
			res = Math.max(res, nums[i]);
		}
		return res;
	}

	// time O(n)
	public int maxSubArrayShort(int[] nums) {
		int max = Integer.MIN_VALUE, localMax = 0;
		for(int i = 0; i < nums.length; i++){
			localMax = Math.max(nums[i], localMax + nums[i]);
			max = Math.max(max, localMax);
		}
		return max;
	}

	// time O(n*log(n)), divide and conquer
	// good picture at https://leetcode.com/problems/maximum-subarray/solution/
	public int maxSubArrayDC(int[] nums) {
		return maxSubArrayUtil(nums, 0, nums.length - 1);
	}

	private int maxSubArrayUtil(int[] nums, int i, int j){
		if(i == j){
			return nums[i];
		}else{
			int mid = i + (j - i) / 2;
			int left = maxSubArrayUtil(nums, i, mid);
			int right = maxSubArrayUtil(nums, mid + 1, j);
			int middle = findMiddleMaxSubarray(nums, mid, i, j);
			return Math.max(left, Math.max(right, middle));
		}
	}

	private int findMiddleMaxSubarray(int[] nums, int m, int i, int j){
		int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE, sum = 0;
		for(int k = m; k >= i; k--){
			sum += nums[k];
			left = Math.max(left, sum);
		}
		sum = 0;
		for(int k = m + 1; k <= j; k++){
			sum += nums[k];
			right = Math.max(right, sum);
		}
		return left + right;
	}
}
