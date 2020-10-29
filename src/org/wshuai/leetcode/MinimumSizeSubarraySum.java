package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0209 https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum {

	// time O(n)
	// same idea as https://leetcode.com/problems/minimum-window-substring/
	public int minSubArrayLen(int s, int[] nums) {
		int n = nums.length, res = Integer.MAX_VALUE, i = 0, j = 0, sum = 0;
		while(i < n){
			sum += nums[i++];
			while(sum >= s){
				res = Math.min(res, i - j);
				sum -= nums[j++];
			}
		}
		return res == Integer.MAX_VALUE ? 0 : res;
	}

	// time O(n*log(n))
	public int minSubArrayLenBinarySearch(int s, int[] nums) {
		int left = 1, right = nums.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(!windowExist(nums, s, mid)){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return windowExist(nums, s, left) ? left : 0;
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
