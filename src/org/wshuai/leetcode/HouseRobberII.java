package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/16.
 * #213 https://leetcode.com/problems/house-robber-ii/
 */
public class HouseRobberII {
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		if (len == 1) {
			return nums[0];
		}
		//Exclude the first and last house respectively
		int max1 = robUtil(nums, 0, len - 2);
		int max2 = robUtil(nums, 1, len - 1);

		return max1 > max2 ? max1 : max2;
	}

	private int robUtil(int[] nums, int s, int e) {
		int len = e - s + 2;
		int[] aux = new int[len];
		aux[0] = 0;
		aux[1] = nums[s];
		for (int i = 2; i < len; i++) {
			aux[i] = Math.max(aux[i - 1], aux[i - 2] + nums[s + i - 1]);
		}
		return aux[len - 1];
	}
}
