package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 06/07/2020.
 * #1471 https://leetcode.com/problems/the-k-strongest-values-in-an-array/
 */
public class TheKStrongestValuesInAnArray {

	// time O(n*log(n))
	public int[] getStrongest(int[] arr, int k) {
		int[] res = new int[k];
		Arrays.sort(arr);
		int n = arr.length, left = 0, right = n - 1, median = arr[(n - 1) >> 1];
		while(k-- > 0){
			int diffLeft = median - arr[left], diffRight = arr[right] - median;
			if(diffLeft > diffRight){
				res[k] = arr[left++];
			}else{
				res[k] = arr[right--];
			}
		}
		return res;
	}
}
