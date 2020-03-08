package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/31/2016.
 * #0324 https://leetcode.com/problems/wiggle-sort-ii/
 */
public class WiggleSortII {
	// time O(n), space O(n)
	public void wiggleSort(int[] nums) {
		if(nums == null || nums.length == 0){
			return;
		}
		int n = nums.length;
		int[] copy = new int[n];
		System.arraycopy(nums, 0, copy, 0, n);
		Arrays.sort(copy);
		int mid = (n - 1) / 2, right = n - 1;
		for(int i = 0; i < n; i++){
			if(i % 2 == 0){
				nums[i] = copy[mid--];
			}else{
				nums[i] = copy[right--];
			}
		}
	}
}
