package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/25/2019.
 * #805 https://leetcode.com/problems/split-array-with-same-average/
 */
public class SplitArrayWithSameAverage {

	public boolean splitArraySameAverage(int[] A) {
		int n = A.length;
		int sum = 0;
		for(int a : A){
			sum += a;
		}
		Arrays.sort(A);
		/*
		sum1 / num1 = (sum - sum1) / (num - num1)
		sum1 * num - sum1 * num1 = sum * num1 - num1 * sum1
		sum1 * num = sum * num1
		sum1 = sum * num1 / num
		 */
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
