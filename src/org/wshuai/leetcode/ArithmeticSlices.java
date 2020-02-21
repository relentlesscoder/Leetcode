package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2016.
 * #0413 https://leetcode.com/problems/arithmetic-slices/
 */
public class ArithmeticSlices {
	// time O(n), space O(1)
	public int numberOfArithmeticSlices(int[] A) {
		int curr = 0;
		int res = 0;
		for(int i = 2; i < A.length; i++){
			if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
				curr += 1;
				res += curr;
			}else{
				curr = 0;
			}
		}
		return res;
	}

	// time O(n), space O(n)
	public int numberOfArithmeticSlicesDP(int[] A) {
		int res = 0;
		int N = A.length;
		if(N < 3){
			return 0;
		}
		int[] dp = new int[N];
		for(int i = 2; i < N; i++){
			// example: [1, 2, 3, 4]
			// the number of slices end at 4 starts at 1, 2
			// add 5 to it,  the number of slices end at 5 starts at 1, 2, 3
			// we have [1, 2, 3, 4, 5], [2, 3, 4, 5], [3, 4, 5]
			// so count(5) = count(4) + 1
			if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
				dp[i] = dp[i - 1] + 1;
			}
			res += dp[i];
		}
		return res;
	}
}
