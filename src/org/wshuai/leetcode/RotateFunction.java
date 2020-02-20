package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2016.
 * #0396 https://leetcode.com/problems/rotate-function/
 */
public class RotateFunction {
	// time O(n)
	public int maxRotateFunction(int[] A) {
		if(A == null || A.length <= 1){
			return 0;
		}
		int n = A.length, sum = 0, cur = 0;
		for(int i = 0; i < n; i++){
			cur += i * A[i];
			sum += A[i];
		}
		int res = cur;
		// F(0) = 0*A[0] + 1*A[1] + ... + (n - 1)*A[n - 1]
		// F(1) = 0*A[n - 1] + 1*A[0] + ... + (n - 1)*A[n - 2]
		// F(2) = 0*A[n - 2] + 1*A[n - 1] + ... + (n - 1)*A[n - 3]
		// ...
		// so F(1) - F(0) = sum - n * A[n - 1]
		//     F(2) - F(1) = sum - n * A[n - 2]
		// ...
		for(int i = 1; i < n; i++){
			int next = cur + sum - n * A[n - i];
			res = Math.max(res, next);
			cur = next;
		}
		return res;
	}
}
