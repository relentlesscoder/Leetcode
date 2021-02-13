package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0209 https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum {

	// time O(n)
	// same idea as https://leetcode.com/problems/minimum-window-substring/
	public int minSubArrayLen(int target, int[] nums) {
		int res = Integer.MAX_VALUE, sum = 0;
		for(int i = 0, j = 0; j < nums.length; j++){
			sum += nums[j];
			while(sum >= target){
				res = Math.min(res, j - i + 1);
				sum -= nums[i++];
			}
		}
		return res == Integer.MAX_VALUE ? 0 : res;
	}

	// time O(n*log(n))
	public int minSubArrayLenBinarySearch(int target, int[] nums) {
		int left = 1, right = nums.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(!windowExist(nums, target, mid)){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return windowExist(nums, target, left) ? left : 0;
	}

	private boolean windowExist(int[] nums, int s, int size){
		int sum = 0;
		for(int i = 0; i < nums.length; i++){
			if(i >= size){
				sum -= nums[i - size];
			}
			sum += nums[i];
			if(sum >= s){
				return true;
			}
		}
		return false;
	}
}
