package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/12/2020.
 * #1502 https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
 */
public class CanMakeArithmeticProgressionFromSequence {

	// time O(n*log(n))
	public boolean canMakeArithmeticProgression(int[] arr) {
		Arrays.sort(arr);
		int diff = arr[1] - arr[0], n = arr.length;
		for(int i = 1; i < n - 1; i++){
			if(arr[i + 1] - arr[i] != diff){
				return false;
			}
		}
		return true;
	}
}
