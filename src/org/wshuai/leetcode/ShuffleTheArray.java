package org.wshuai.leetcode;

/**
 * Created by Wei on 06/07/2020.
 * #1470 https://leetcode.com/problems/shuffle-the-array/
 */
public class ShuffleTheArray {

	// time O(n)
	public int[] shuffle(int[] nums, int n) {
		int[] res = new int[n << 1];
		for(int i = 0, j = 0; i < n; i++){
			res[j++] = nums[i];
			res[j++] = nums[i + n];
		}
		return res;
	}
}
