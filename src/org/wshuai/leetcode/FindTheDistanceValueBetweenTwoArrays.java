package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 03/22/2020.
 * #1385 https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
 */
public class FindTheDistanceValueBetweenTwoArrays {
	// time O(n*log(m))
	public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
		int res = 0, n = arr2.length;
		Arrays.sort(arr2);
		for(int num : arr1){
			if(num <= arr2[0] && arr2[0] - num > d){
				res++;
			}else if(num >= arr2[n - 1] && num - arr2[n - 1] > d){
				res++;
			}else{
				res += binarySearch(arr2, num, d) ? 1 : 0;
			}
		}
		return res;
	}

	private boolean binarySearch(int[] arr, int num, int d){
		int l = 0, r = arr.length - 1;
		while(l < r){
			int m = l + (r - l) / 2;
			if(arr[m] < num){
				l = m + 1;
			}else{
				r = m;
			}
		}
		if(l < arr.length && arr[l] - num <= d){
			return false;
		}
		if(l > 0 && num - arr[l - 1] <= d){
			return false;
		}
		return true;
	}
}
