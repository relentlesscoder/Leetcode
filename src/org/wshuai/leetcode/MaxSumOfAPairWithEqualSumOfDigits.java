package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/28/2023.
 * #2342 https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
 */
public class MaxSumOfAPairWithEqualSumOfDigits {

	// time O(n + d), space O(d)
	public int maximumSum(int[] nums) {
		int res = -1;
		Map<Integer, int[]> map = new HashMap<>();
		for (int num : nums) {
			int digitSum = getDigitSum(num);
			map.computeIfAbsent(digitSum, v -> new int[]{-1, -1});
			int[] vals = map.get(digitSum);
			if (num > vals[0]) {
				vals[1] = vals[0];
				vals[0] = num;
			} else if (num > vals[1]) {
				vals[1] = num;
			}
		}
		for (int key : map.keySet()) {
			int[] vals = map.get(key);
			if (vals[0] == -1 || vals[1] == -1) {
				continue;
			}
			res = Math.max(res, vals[0] + vals[1]);
		}
		return res;
	}

	private int getDigitSum(int num) {
		int res = 0;
		while (num > 0) {
			res += num % 10;
			num /= 10;
		}
		return res;
	}
}
