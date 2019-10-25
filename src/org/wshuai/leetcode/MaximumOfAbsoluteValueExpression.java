package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2019.
 * #1131 https://leetcode.com/problems/maximum-of-absolute-value-expression/
 */
public class MaximumOfAbsoluteValueExpression {

	// https://leetcode.com/problems/maximum-of-absolute-value-expression/discuss/340075/c%2B%2B-beats-100-(both-time-and-memory)-with-algorithm-and-image
	public int maxAbsValExpr(int[] arr1, int[] arr2) {
		int n = arr1.length;
		int[] sum1 = new int[n];
		int[] sum2 = new int[n];
		int[] diff1 = new int[n];
		int[] diff2 = new int[n];
		for(int i = 0; i < n; i++){
			sum1[i] = arr1[i] + arr2[i] + i;
			sum2[i] = arr1[i] + arr2[i] - i;
			diff1[i] = arr1[i] - arr2[i] + i;
			diff2[i] = arr1[i] - arr2[i] - i;
		}
		return Math.max(Math.max(maxDiff(sum1), maxDiff(sum2)),
				Math.max(maxDiff(diff1), maxDiff(diff2)));
	}

	private int maxDiff(int[] arr){
		int max = arr[0];
		int min = arr[0];
		for(int v: arr){
			max = Math.max(v, max);
			min = Math.min(v, min);
		}
		return max - min;
	}
}
