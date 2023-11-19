package org.wshuai.leetcode;

/**
 * Created by Wei on 09/07/2020.
 * #1574 https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
 */
public class ShortestSubarrayToBeRemovedToMakeArraySorted {

	// time O(n)
	public int findLengthOfShortestSubarray(int[] arr) {
		int n = arr.length, left = 0, right = n - 1;
		while(left + 1 < n && arr[left] <= arr[left + 1]){
			left++;
		}
		if(left == n - 1){
			return 0;
		}
		while(right >= 1 && arr[right - 1] <= arr[right]){
			right--;
		}
		int res = Math.min(n - left - 1, right), i = 0, j = right;
		while(i <= left && j < n){
			if(arr[i] <= arr[j]){
				res = Math.min(res, j - i - 1);
				i++;
			}else{
				j++;
			}
		}
		return res;
	}
}
