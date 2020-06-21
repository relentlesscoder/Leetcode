package org.wshuai.leetcode;

/**
 * Created by Wei on 12/17/2019.
 * #0768 https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
 */
public class MaxChunksToMakeSortedII {

	// time O(n), space O(n)
	public int maxChunksToSorted(int[] arr) {
		int res = 0, n = arr.length, maxFromLeft = Integer.MIN_VALUE;
		int[] minFromRight = new int[n];
		minFromRight[n - 1] = arr[n - 1];
		for(int i = n - 2; i >= 0; i--){
			minFromRight[i] = Math.min(minFromRight[i + 1], arr[i]);
		}
		for(int i = 0; i < n; i++){
			if(maxFromLeft <= minFromRight[i]){
				res++;
			}
			maxFromLeft = Math.max(maxFromLeft, arr[i]);
		}
		return res;
	}
}
