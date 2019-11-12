package org.wshuai.leetcode;

/**
 * Created by Wei on 11/9/2019.
 * #1186 https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
 */
public class MaximumSubarraySumWithOneDeletion {
	public int maximumSum(int[] arr) {
		int N = arr.length;
		int[] maxSumLeft = new int[N];
		int[] maxSumRight = new int[N];
		int maxOverall = arr[0];
		maxSumLeft[0] = arr[0];
		for(int i = 1; i < N; i++){
			maxSumLeft[i] = Math.max(arr[i], maxSumLeft[i - 1] + arr[i]);
			maxOverall = Math.max(maxOverall, maxSumLeft[i]);
		}
		maxSumRight[N - 1] = arr[N - 1];
		for(int i = N - 2; i >= 0; i--){
			maxSumRight[i] = Math.max(arr[i],  maxSumRight[i + 1] + arr[i]);
		}
		for(int i = 1; i < N - 1; i++){
			maxOverall = Math.max(maxOverall, maxSumLeft[i - 1] + maxSumRight[i + 1]);
		}
		return maxOverall;
	}
}
