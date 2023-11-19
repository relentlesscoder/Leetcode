package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2020.
 * #1588 https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
 */
public class SumOfAllOddLengthSubarrays {

	// time O(n^2)
	public int sumOddLengthSubarrays(int[] arr) {
		int res = 0, n = arr.length;
		for(int i = 0; i < n; i++){
			int sum = 0;
			for(int j = i; j < n; j++){
				sum += arr[j];
				if((j - i + 1) % 2 == 1){
					res += sum;
				}
			}
		}
		return res;
	}
}
