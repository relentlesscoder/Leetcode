package org.wshuai.leetcode;

/**
 * Created by Wei on 04/26/2020.
 * #1423 https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 */
public class MaximumPointsYouCanObtainFromCards {
	// time O(n)
	public int maxScore(int[] cardPoints, int k) {
		int res = 0, sum = 0, n = cardPoints.length;
		// result is the sum of the two subarray [x -> n - 1] and [0 -> y] which has a
		// total length of k
		for(int i = n - k; i < n; i++){
			sum += cardPoints[i];
		}
		for(int j = 0, i = n - k; j < k; j++){
			res = Math.max(res, sum);
			sum += cardPoints[j] - cardPoints[i++];
		}
		return Math.max(res, sum);
	}
}
