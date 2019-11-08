package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/7/2019.
 * #962 https://leetcode.com/problems/maximum-width-ramp/
 */
public class MaximumWidthRamp {
	public int maxWidthRamp(int[] A) {
		int N = A.length;
		Integer[] arr = new Integer[N];
		for(int i = 0; i < N; i++){
			arr[i] = i;
		}
		Arrays.sort(arr, (a, b) -> A[a] - A[b]);
		int res = 0;
		int max = arr[N - 1];
		for(int i = N - 1; i >= 0; i--){
			if(max >= arr[i]){
				res = Math.max(res, max - arr[i]);
			}else{
				max = arr[i];
			}
		}
		return res;
	}
}
