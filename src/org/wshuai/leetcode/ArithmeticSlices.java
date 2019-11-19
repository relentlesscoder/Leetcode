package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/16.
 * #413 https://leetcode.com/problems/arithmetic-slices/
 */
public class ArithmeticSlices {
	public int numberOfArithmeticSlices(int[] A) {
		int curr = 0;
		int res = 0;
		for(int i = 2; i < A.length; i++){
			// since curr starts to accumulate from the third number
			// curr = LAS (the length of the current arithmetic slice) - 2
			// each time we extend the current arithmetic slice
			// we have LAS - 2 new arithmetic slice so we simply add curr
			// to the result.
			// example: [1, 2, 3, 4]
			// add 5 to it we have [1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5]
			if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
				curr += 1;
				res += curr;
			}else{
				curr = 0;
			}
		}
		return res;
	}

	public int numberOfArithmeticSlicesDP(int[] A) {
		int res = 0;
		int N = A.length;
		if(N < 3){
			return 0;
		}
		int[] dp = new int[N];
		for(int i = 2; i < N; i++){
			if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
				dp[i] = dp[i - 1] + 1;
			}
			/*
			else{
				dp[i] = 0;
			}
			*/
			res += dp[i];
		}
		return res;
	}
}
