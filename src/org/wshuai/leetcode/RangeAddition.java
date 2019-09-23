package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/16.
 * #370 https://leetcode.com/problems/range-addition/
 */
public class RangeAddition {
	//O(n+k)
	public int[] getModifiedArray(int length, int[][] updates) {
		int[] result = new int[length];
		int len = updates.length;
		for (int i = 0; i < len; i++) {
			int start = updates[i][0];
			int end = updates[i][1];
			int inc = updates[i][2];
			if (start < length) {
				result[start] += inc;
			}
			if (end < length - 1) {
				result[end + 1] -= inc;
			}
		}

		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += result[i];
			result[i] = sum;
		}
		return result;
	}
}
