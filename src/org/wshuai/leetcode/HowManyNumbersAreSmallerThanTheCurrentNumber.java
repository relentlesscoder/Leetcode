package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 03/01/2020.
 * #1365 https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber {
	// time O(n*log(n)), space O(n)
	public int[] smallerNumbersThanCurrent(int[] nums) {
		int n = nums.length;
		int[] res = new int[n], copy = nums.clone();
		Arrays.sort(copy);
		for(int i = 0; i < n; i++){
			res[i] = binarySearch(copy, nums[i]);
		}
		return res;
	}

	private int binarySearch(int[] arr, int target){
		int left = 0, right = arr.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(arr[mid] < target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}
}
