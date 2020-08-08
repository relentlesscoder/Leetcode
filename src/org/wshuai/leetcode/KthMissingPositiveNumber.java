package org.wshuai.leetcode;

/**
 * Created by Wei on 08/08/2020.
 * #1539 https://leetcode.com/problems/kth-missing-positive-number/
 */
public class KthMissingPositiveNumber {

	// time O(log(n))
	public int findKthPositive(int[] arr, int k) {
		int left = 0, right = arr.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(arr[mid] - (mid + 1) >= k){
				right = mid;
			}else{
				left = mid + 1;
			}
		}
		return left + k;
	}
}
