package org.wshuai.leetcode;

/**
 * Created by Wei on 09/29/2023.
 * #2367 https://leetcode.com/problems/number-of-arithmetic-triplets/
 */
public class NumberOfArithmeticTriplets {

	// time O(n), space O(1)
	public int arithmeticTriplets(int[] nums, int diff) {
		int res = 0, doubleDiff = (diff << 1);
		int[] numberMap = new int[201];
		for (int num : nums) {
			if (num >= doubleDiff && numberMap[num - diff] == 1 && numberMap[num - doubleDiff] == 1) {
				res++;
			}
			numberMap[num] = 1;
		}
		return res;
	}
}
