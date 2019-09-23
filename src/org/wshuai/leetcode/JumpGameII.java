package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/9/16.
 * #45 https://leetcode.com/problems/jump-game-ii/
 */
public class JumpGameII {

	//O(n)
	public int jump(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		int min = 0;
		int curr = 0;
		int last = 0;
		for (int i = 0; i < len; i++) {
			if (i > last) {
				last = curr;
				min++;
			}
			curr = Math.max(curr, i + nums[i]);
		}
		return min;
	}

	//TLE
	public int jumpDP(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		int[] aux = new int[len];
		Arrays.fill(aux, Integer.MAX_VALUE);
		aux[len - 1] = 0;
		for (int i = len - 2; i >= 0; i--) {
			int step = nums[i];
			int min = Integer.MAX_VALUE;
			for (int j = 1; j <= step; j++) {
				if (i + j < len && aux[i + j] != Integer.MAX_VALUE) {
					min = Math.min(1 + aux[i + j], min);
				}
			}
			aux[i] = min;
		}
		return aux[0];
	}
}
