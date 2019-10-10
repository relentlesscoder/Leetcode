package org.wshuai.leetcode;

/**
 * Created by Wei on 8/8/19.
 * #922 https://leetcode.com/problems/sort-array-by-parity-ii/
 */
public class SortArrayByParityII {
	public int[] sortArrayByParityII(int[] A) {
		int odd = 1;
		for (int even = 0; even < A.length; even += 2) {
			if (A[even] % 2 == 1) {
				while (A[odd] % 2 == 1) {
					odd += 2;
				}

				int tmp = A[even];
				A[even] = A[odd];
				A[odd] = tmp;
			}
		}
		return A;
	}
}
