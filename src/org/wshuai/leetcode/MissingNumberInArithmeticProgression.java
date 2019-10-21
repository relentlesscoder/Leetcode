package org.wshuai.leetcode;

/**
 * Created by Wei on 10/20/2019.
 * #1228 https://leetcode.com/problems/missing-number-in-arithmetic-progression/
 */
public class MissingNumberInArithmeticProgression {
	public int missingNumber(int[] arr) {
		int diff = arr[arr.length - 1] - arr[0];
		int num = arr.length;
		int d = diff / num;
		if(d == 0){
			return arr[0];
		}
		for(int i = 1; i < arr.length; i++){
			if(arr[i] - arr[i - 1] != d){
				return arr[i - 1] + d;
			}
		}
		return -1;
	}
}
