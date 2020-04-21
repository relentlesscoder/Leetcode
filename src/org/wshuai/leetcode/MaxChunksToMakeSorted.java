package org.wshuai.leetcode;

/**
 * Created by Wei on 10/14/2019.
 * #0769 https://leetcode.com/problems/max-chunks-to-make-sorted/
 */
public class MaxChunksToMakeSorted {
	// time O(n)
	// similar to #763
	public int maxChunksToSorted(int[] arr) {
		int res = 0, min = 0, max = 0;
		for(int i = 0; i < arr.length; i++){
			max = Math.max(arr[i], max);
			// if current chunk contains all numbers in [min, max], we can split it
			if(max == i){
				res++;
				min = max = i + 1;
			}
		}
		return res;
	}
}
