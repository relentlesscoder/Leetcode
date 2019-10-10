package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 8/9/19.
 * #1099 https://leetcode.com/problems/two-sum-less-than-k/
 */
public class TwoSumLessThanK {
	public int twoSumLessThanK(int[] A, int K) {
		Arrays.sort(A);
		int left = 0;
		int right = A.length - 1;
		int max = Integer.MIN_VALUE;
		while (left < right) {
			int sum = A[left] + A[right];
			if (sum >= K) {
				right--;
			} else {
				max = sum > max ? sum : max;
				left++;
			}
		}
		return max == Integer.MIN_VALUE ? -1 : max;
	}
}
