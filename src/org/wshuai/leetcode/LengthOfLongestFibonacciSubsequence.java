package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/7/19.
 * #873 https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/
 */
public class LengthOfLongestFibonacciSubsequence {
	public int lenLongestFibSubseq(int[] A) {
		int N = A.length;
		Map<Integer, Integer> index = new HashMap<>();
		for(int i = 0; i < N; i++){
			index.put(A[i], i);
		}

		Map<Integer, Integer> longest = new HashMap<>();
		int ans = 0;

		for(int k = 0; k < N; k++){
			for(int j = 0; j < k; j++){
				int i = index.getOrDefault(A[k] - A[j], -1);
				if(i >= 0 && i < j){
					int cand = longest.getOrDefault(i * N + j, 2) + 1;
					longest.put(j * N + k, cand);
					ans = Math.max(ans, cand);
				}
			}
		}

		return ans >= 3 ? ans : 0;
	}
}
