package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/31/2016.
 * #0280 https://leetcode.com/problems/wiggle-sort/
 */
public class WiggleSort {

	// time O(n)
	// https://leetcode.com/problems/wiggle-sort/discuss/71693/My-explanations-of-the-best-voted-Algo
	public void wiggleSort(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int len = nums.length;
		for (int i = 1; i < len; i++) {
			if ((i % 2 == 1 && nums[i] < nums[i - 1])
					|| (i % 2 == 0 && nums[i] > nums[i - 1])) {
				int temp = nums[i];
				nums[i] = nums[i - 1];
				nums[i - 1] = temp;
			}
		}
	}

	// time O(n*log(n))
	public void wiggleSortSwap(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length;
		if(n < 3){
			return;
		}
		for(int i = 1, j = 2; j < n; i += 2, j += 2){
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
	}

}
