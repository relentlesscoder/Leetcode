package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 12/25/2019.
 * #0805 https://leetcode.com/problems/split-array-with-same-average/
 */
public class SplitArrayWithSameAverage {

	/* math induction
	s1 / k
	s2 / n - k
	s1 + s2 / n

	s1*n - s1*k = s2*k
	s1*n = (s1 + s2)*k
	(s1 + s2)/n = s1/k

	(s1+s2)*k%n == 0
	*/

	// time O(n^3)
	// https://leetcode.com/problems/split-array-with-same-average/discuss/120667/C%2B%2B-Solution-with-explanation-early-termination-(Updated-for-new-test-case)
	public boolean splitArraySameAverage(int[] A) {
		int n = A.length, m = n >> 1, sum = 0;
		for(int a : A){
			sum += a;
		}
		boolean isPossible = false;
		// use the above math conclusion to apply
		// the early check
		for(int i = 1; i <= m && !isPossible; i++){
			if(sum*i%n == 0){
				isPossible = true;
			}
		}
		if(!isPossible){
			return false;
		}
		// dp[i] stores all the possible combination sum
		// of size of the subset in [0, i]
		Set<Integer>[] dp = new HashSet[m + 1];
		for(int i = 0; i <= m; i++){
			dp[i] = new HashSet<>();
		}
		dp[0].add(0);
		// for each number a
		for(int a : A){
			// for i from 1 to n/2
			for(int i = m; i >= 1; i--){
				// add a to all the possible combination sum of i - 1
				for(int t : dp[i - 1]){
					dp[i].add(t + a);
				}
			}
		}
		// final check to validate if there is a possible answer
		for(int k = 1; k <= m; k++){
			if(sum*k%n == 0 && dp[k].contains(sum*k/n)){
				return true;
			}
		}
		return false;
	}

	public boolean splitArraySameAverageDFS(int[] A) {
		int n = A.length, sum = 0;
		for(int a : A){
			sum += a;
		}
		Arrays.sort(A);
		for(int k = 1; k <= n/2; k++){
			if(sum * k % n == 0 && dfs(A, n - 1, k, sum * k / n)){
				return true;
			}
		}
		return false;
	}

	private boolean dfs(int[] A, int idx, int len, int target){
		if(len == 0){
			return target == 0;
		}
		// Optimization, A is sorted from large to small
		if(target > len * A[idx]){
			return false;
		}
		for(int i = idx; i >= len - 1; i--){
			if(A[i] <= target && dfs(A, i - 1, len - 1, target - A[i])){
				return true;
			}
		}
		return false;
	}
}
