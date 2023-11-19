package org.wshuai.leetcode;

/**
 * Created by Wei on 09/30/2019.
 * #1151 https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
 */
public class MinimumSwapsToGroupAllOnesTogether {

	// time O(n), space O(1)
	public int minSwaps(int[] data) {
		int res = data.length, n = data.length, ones = 0, count = 0;
		for (int d : data) {
			ones += d;
		}
		for (int i = 0, j = 0; i < n; i++) {
			while (j < n && j - i + 1 <= ones) {
				count += data[j++];
			}
			res = Math.min(res, ones - count);
			count -= data[i];
		}
		return res;
	}
}
