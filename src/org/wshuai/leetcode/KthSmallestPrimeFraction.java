package org.wshuai.leetcode;

/**
 * Created by Wei on 12/25/2019.
 * #786 https://leetcode.com/problems/k-th-smallest-prime-fraction/
 */
public class KthSmallestPrimeFraction {
	// https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378
	public int[] kthSmallestPrimeFraction(int[] A, int K) {
		double l = 0, r = 1;
		int p = 0, q = 1;

		for (int n = A.length, cnt = 0; true; cnt = 0, p = 0) {
			double m = (l + r) / 2;

			// from left to right, find all fractions A[i]/A[j] <= m
			// no need to reset j for each i given the array is sorted
			for (int i = 0, j = 1; i < n; i++) {
				while (j < n && A[i] > m * A[j]){
					j++;
				}
				cnt += (n - j);

				// record the max fraction
				if (j < n && p * A[j] < q * A[i]) {
					p = A[i];
					q = A[j];
				}
			}

			// if number of fraction less or equal to current middle value
			// we find the solution already
			if (cnt < K) {
				l = m;
			} else if (cnt > K) {
				r = m;
			} else {
				return new int[] {p, q};
			}
		}
	}
}
