package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/27/2021.
 * #1753 https://leetcode.com/problems/maximum-score-from-removing-stones/
 */
public class MaximumScoreFromRemovingStones {

	public int maximumScore(int a, int b, int c) {
		int[] arr = new int[]{a, b, c};
		Arrays.sort(arr);
		int res = 0, sum = arr[0] + arr[1];
		if(sum > arr[2]){
			res = arr[2] + (sum - arr[2]) / 2;
		}else{
			res = sum;
		}
		return res;
	}
}
