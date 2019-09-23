package org.wshuai.leetcode;

/**
 * Created by Wei on 9/12/2019.
 * #1004 https://leetcode.com/problems/max-consecutive-ones-iii/
 */
public class MaxConsecutiveOnesIII {
	public int longestOnes(int[] A, int K) {
		int i = 0;
		int j = 0;
		int count = 0;
		int longest = 0;
		while (j < A.length) {
			if (A[j] == 0) {
				count++;
			}
			if (count > K) {
				longest = Math.max(j - i, longest);
				while (i < j && A[i] != 0) {
					i++;
				}
				i++;
				count--;
			}
			j++;
		}
		// calculate the last one (if any)
		if (count <= K) {
			longest = Math.max(j - i, longest);
		}
		return longest;
	}
}
