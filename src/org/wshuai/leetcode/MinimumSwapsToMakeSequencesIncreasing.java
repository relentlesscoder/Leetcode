package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/2019.
 * #801 https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
 */
public class MinimumSwapsToMakeSequencesIncreasing {
	// https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119879/C%2B%2BJavaPython-DP-O(N)-Solution
	public int minSwap(int[] A, int[] B) {
		int N = A.length;
		int[] swap = new int[N];
		int[] nonswap = new int[N];
		swap[0] = 1;
		for(int i = 1; i < N; i++){
			swap[i] = nonswap[i] = N;
			if(A[i - 1] < A[i] && B[i - 1] < B[i]){
				swap[i] = swap[i - 1] + 1;
				nonswap[i] = nonswap[i - 1];
			}
			if(A[i - 1] < B[i] && B[i - 1] < A[i]){
				swap[i] = Math.min(swap[i], nonswap[i - 1] + 1);
				nonswap[i] = Math.min(nonswap[i], swap[i - 1]);
			}
		}
		return Math.min(swap[N - 1], nonswap[N - 1]);
	}
}
