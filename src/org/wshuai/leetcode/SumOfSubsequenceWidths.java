package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 1/1/2020.
 * #891 https://leetcode.com/problems/sum-of-subsequence-widths/
 */
public class SumOfSubsequenceWidths {
	// https://leetcode.com/problems/sum-of-subsequence-widths/discuss/161267/C%2B%2BJava1-line-Python-Sort-and-One-Pass
	public int sumSubseqWidths(int[] A) {
		Arrays.sort(A);
		long c = 1, res = 0, mod = (long)1e9 + 7;
		for (int i = 0; i < A.length; ++i, c = (c << 1) % mod){
			res = (res + A[i] * c - A[A.length - i - 1] * c) % mod;
		}
		return (int)((res + mod) % mod);
	}
}
