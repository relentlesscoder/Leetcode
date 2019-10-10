package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/19.
 * #509 https://leetcode.com/problems/fibonacci-number/
 */
public class FibonacciNumber {
	public int fib(int N) {
		if (N < 2) {
			return N;
		}
		int[] res = new int[N + 1];
		res[0] = 0;
		res[1] = 1;
		for (int i = 2; i <= N; i++) {
			res[i] = res[i - 1] + res[i - 2];
		}
		return res[N];
	}
}
