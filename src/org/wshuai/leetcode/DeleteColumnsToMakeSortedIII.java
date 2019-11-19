package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/19/16.
 * #960 https://leetcode.com/problems/delete-columns-to-make-sorted-iii/
 */
public class DeleteColumnsToMakeSortedIII {
	// the problem equals to find the longest increasing subsequence
	// cross all the strings
	public int minDeletionSize(String[] A) {
		int M = A.length;
		int N = A[0].length();
		int res = N - 1;
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		int k;
		for(int j = 0; j < N; j++){
			for(int i = 0; i < j; i++){
				for(k = 0; k < M; k++){
					if(A[k].charAt(i) > A[k].charAt(j)){
						break;
					}
				}
				if(k == M && dp[i] + 1 > dp[j]){
					dp[j] = dp[i] + 1;
				}
			}
			res = Math.min(res, N - dp[j]);
		}
		return res;
	}
}
