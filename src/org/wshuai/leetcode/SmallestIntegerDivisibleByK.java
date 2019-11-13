package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2019.
 * #1015 https://leetcode.com/problems/smallest-integer-divisible-by-k/
 */
public class SmallestIntegerDivisibleByK {
	// https://www.youtube.com/watch?v=AxVUCzee-XI
	public int smallestRepunitDivByK(int K) {
		if (K % 2 == 0 || K % 5 == 0) return -1;
		int r = 0;
		for (int N = 1; N <= K; ++N) {
			r = (r * 10 + 1) % K;
			if (r == 0) return N;
		}
		return -1;
	}
}
