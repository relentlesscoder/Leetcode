package org.wshuai.leetcode;

/**
 * Created by Wei on 12/17/2019.
 * #768 https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
 */
public class MaxChunksToMakeSortedII {
	public int maxChunksToSorted(int[] arr) {
		int n = arr.length;
		int[] minFromRight = new int[n];

		minFromRight[n - 1] = arr[n - 1];
		for(int i = n - 2; i >= 0; i--){
			minFromRight[i] = Math.min(minFromRight[i + 1], arr[i]);
		}
		int res = 0;
		int maxFromLeft = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++){
			if(maxFromLeft <= minFromRight[i]){
				res++;
			}
			maxFromLeft = Math.max(arr[i], maxFromLeft);
		}
		return res;
	}
}
