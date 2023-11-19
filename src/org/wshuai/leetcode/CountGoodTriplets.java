package org.wshuai.leetcode;

/**
 * Created by Wei on 08/02/2020.
 * #1534 https://leetcode.com/problems/count-good-triplets/
 */
public class CountGoodTriplets {

	// time O(n^3)
	public int countGoodTriplets(int[] arr, int a, int b, int c) {
		int res = 0, n = arr.length;
		for(int j = 1; j < n - 1; j++){
			for(int i = 0; i < j; i++){
				if(Math.abs(arr[i] - arr[j]) > a){
					continue;
				}
				for(int k = j + 1; k < n; k++){
					if(Math.abs(arr[j] - arr[k]) <= b
						&& Math.abs(arr[i] - arr[k]) <= c){
						res++;
					}
				}
			}
		}
		return res;
	}
}
