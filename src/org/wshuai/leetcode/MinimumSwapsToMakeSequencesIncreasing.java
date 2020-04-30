package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2019.
 * #0801 https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
 */
public class MinimumSwapsToMakeSequencesIncreasing {

	// time O(n), space O(n)
	// https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/270616/Java-DP-2ms-solution-with-comments
	// https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119879/C%2B%2BJavaPython-DP-O(N)-Solution
	public int minSwap(int[] A, int[] B) {
		int n = A.length;
		int[] swap = new int[n], noswap = new int[n];
		swap[0] = 1;
		for(int i = 1; i < n; i++){
			swap[i] = noswap[i] = n;
			if(A[i - 1] < A[i] && B[i - 1] < B[i]){
				swap[i] = swap[i - 1] + 1; //swap both A[i - 1], B[i - 1] & A[i], B[i]
				noswap[i] = noswap[i - 1]; //don't swap both A[i - 1], B[i - 1] & A[i], B[i]
			}
			if(A[i - 1] < B[i] && B[i - 1] < A[i]){
				swap[i] = Math.min(swap[i], noswap[i - 1] + 1); //if we swap A[i],B[i], we don't need to swap A[i - 1],B[i - 1]
				// not_swap[i - 1] + 1 because we didn't swap A[i - 1] & B[i - 1] and +1 for current swap
				noswap[i] = Math.min(noswap[i], swap[i - 1]); //if we swap A[i - 1],B[i - 1], we don't need to swap A[i],B[i]
			}
		}
		return Math.min(swap[n - 1], noswap[n - 1]);
	}
}
