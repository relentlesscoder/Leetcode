package org.wshuai.leetcode;

/**
 * Created by Wei on 09/12/2019.
 * #1004 https://leetcode.com/problems/max-consecutive-ones-iii/
 */
public class MaxConsecutiveOnesIII {

	// time O(n)
	public int longestOnes(int[] A, int K) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int res = 0, n = A.length;
		for(int i = 0, j = 0, zeros = 0; j < n; j++){
			zeros += A[j] == 0 ? 1 : 0;
			while(zeros > K){
				zeros -= A[i++] == 0 ? 1 : 0;
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}
}
