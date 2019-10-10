package org.wshuai.leetcode;

/**
 * Created by Wei on 10/3/16.
 */
public class RotateFunction {

	// 6ms
	// F(1) - F(0) = SUM(A) - 4*A[3];
	// F(2) - F(1) = SUM(A) - 4*A[2];
	// F(3) - F(2) = SUM(A) - 4*A[1];
	public int maxRotateFunction(int[] A) {
		if (A == null || A.length <= 1) {
			return 0;
		}
		int len = A.length;
		int sum = 0;
		int fval = 0;
		for (int i = 0; i < len; i++) {
			sum += A[i];
			fval += i * A[i];
		}
		int max = fval;
		int idx = len - 1;
		for (int i = 1; i < len; i++) {
			int val = fval + sum - len * A[idx];
			max = val > max ? val : max;
			idx--;
			fval = val;
		}

		return max;
	}

	//860 ms
	public int maxRotateFunctionNaive(int[] A) {
		if (A == null || A.length <= 1) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int len = A.length;
		int j = 0;
		while (j < len) {
			int sum = 0;
			for (int i = 0; i < len; i++) {
				int x = i - j;
				x = (x >= 0) ? x : (x + len);
				sum += i * A[x];
			}
			max = sum > max ? sum : max;
			j++;
		}

		return max;
	}
}
