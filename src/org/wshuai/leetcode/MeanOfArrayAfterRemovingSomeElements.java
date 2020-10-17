package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/17/2020.
 * #1619 https://leetcode.com/problems/mean-of-array-after-removing-some-elements/
 */
public class MeanOfArrayAfterRemovingSomeElements {

	// time O(n*log(n))
	public double trimMean(int[] arr) {
		Arrays.sort(arr);
		int n = arr.length, r = n * 5 / 100, sum = 0, c = 0;
		for(int i = r; i < n - r; i++){
			sum += arr[i];
			c++;
		}
		return (sum * 1.0) / c;
	}
}
