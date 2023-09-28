package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/27/2023.
 * #2553 https://leetcode.com/problems/separate-the-digits-in-an-array/
 */
public class SeparateTheDigitsInAnArray {

	// time O(n), space O(n)
	public int[] separateDigits(int[] nums) {
		List<Integer> digits = new ArrayList<>();
		for (int i = nums.length - 1; i >= 0; i--) {
			int val = nums[i];
			while (val > 0) {
				digits.add(val % 10);
				val /= 10;
			}
		}
		int[] res = new int[digits.size()];
		for (int i = 0, j = digits.size() - 1; i < digits.size(); i++, j--) {
			res[j] = digits.get(i);
		}
		return res;
	}
}
