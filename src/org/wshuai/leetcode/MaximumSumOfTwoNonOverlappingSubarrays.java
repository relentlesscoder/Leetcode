package org.wshuai.leetcode;

/**
 * Created by Wei on 9/24/19.
 * #1031 https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
 */
public class MaximumSumOfTwoNonOverlappingSubarrays {
	//L array appearing first and then M array
	//or
	//M array appearing first and then L array
	public int maxSumTwoNoOverlap(int[] A, int L, int M) {
		int max = 0;
		int[] prefix = new int[A.length + 1];
		prefix[0] = 0;
		for(int i = 0; i < A.length; i++){
			prefix[i + 1] = prefix[i] + A[i];
		}
		int res = prefix[L + M];
		int lMax = prefix[L];
		int mMax = prefix[M];
		for(int i = L + M; i < A.length; i++){
			lMax = Math.max(lMax, prefix[i + 1 - M] - prefix[i + 1 - M - L]);
			mMax = Math.max(mMax, prefix[i + 1 - L] - prefix[i + 1 - M - L]);
			res = Math.max(res, Math.max(lMax + prefix[i + 1] - prefix[i + 1 - M], mMax + prefix[i + 1] - prefix[i + 1 - L]));
		}
		return res;
	}
}
