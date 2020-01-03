package org.wshuai.leetcode;

/**
 * Created by Wei on 12/17/2019.
 * #992 https://leetcode.com/problems/subarrays-with-k-different-integers/
 */
public class SubarraysWithKDifferentIntegers {
	// https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/234482/JavaC%2B%2BPython-Sliding-Window-atMost(K)-atMost(K-1)
	public int subarraysWithKDistinct(int[] A, int K) {
		return atMostK(A, K) - atMostK(A, K - 1);
	}

	private int atMostK(int[] A, int K) {
		int i = 0, res = 0;
		int[] count = new int[A.length];
		for (int j = 0; j < A.length; ++j) {
			if (count[A[j] - 1] == 0){
				K--;
			}
			count[A[j] - 1]++;
			while (K < 0) {
				count[A[i] - 1]--;
				if (count[A[i] - 1] == 0) {
					K++;
				}
				i++;
			}
			res += j - i + 1;
		}
		return res;
	}
}
