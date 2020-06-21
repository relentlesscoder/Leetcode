package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/09/2019.
 * #0823 https://leetcode.com/problems/binary-trees-with-factors/
 */
public class BinaryTreesWithFactors {

	private static final int MOD = 1_000_000_007;

	// time O(n^2), space O(n)
	public int numFactoredBinaryTrees(int[] A) {
		Arrays.sort(A);
		Map<Integer, Long> dp = new HashMap<>();
		for(int i = 0; i < A.length; i++){
			long count = 1;
			int j = 0, k = i - 1;
			while(j <= k){
				int prod = A[j] * A[k];
				if(prod == A[i]){
					if(A[j] == A[k]){ // left child and the right child are same, then we have n*n combinations
						count = (count + dp.get(A[j]) * dp.get(A[k]) % MOD) % MOD;
					}else{ // left child and the right child are not same, then we have n*n*2 combinations since we swap
						// left and right subtree
						count = (count + ((dp.get(A[j]) * dp.get(A[k]) % MOD) * 2 % MOD)) % MOD;
					}
					j++;
					k--;
				}else if(prod < A[i]){
					j++;
				}else{
					k--;
				}
			}
			dp.put(A[i], count);
		}
		long res = 0;
		for(long count : dp.values()){
			res = (res + count) % MOD;
		}
		return (int)res;
	}
}
