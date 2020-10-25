package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/25/2020.
 * #1630 https://leetcode.com/problems/arithmetic-subarrays/
 */
public class ArithmeticSubarrays {

	// time O(n*log(n)*q), space O(n*q)
	public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
		List<Boolean> res = new ArrayList<>();
		for(int i = 0; i < l.length; i++){
			res.add(isArithmetic(Arrays.copyOfRange(nums, l[i], r[i] + 1)));
		}
		return res;
	}

	private boolean isArithmetic(int[] arr){
		if(arr.length < 2){
			return false;
		}
		if(arr.length == 2){
			return true;
		}
		Arrays.sort(arr);
		for(int i = 2; i < arr.length; i++){
			if(arr[i] - arr[i - 1] != arr[1] - arr[0]){
				return false;
			}
		}
		return true;
	}
}
