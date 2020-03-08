package org.wshuai.leetcode;

/**
 * Created by Wei on 03/07/2017.
 * #0477 https://leetcode.com/problems/total-hamming-distance/
 */
public class TotalHammingDistance {
	// time O(n)
	public int totalHammingDistance(int[] nums) {
		int res = 0, ones = 0, n = nums.length;
		for(int i = 0; i < 32; i++){
			ones = 0;
			for(int j = 0; j < n; j++){
				ones += (nums[j] & 1);
				nums[j] >>= 1;
			}
			res += ones * (n - ones);
		}
		return res;
	}
}
