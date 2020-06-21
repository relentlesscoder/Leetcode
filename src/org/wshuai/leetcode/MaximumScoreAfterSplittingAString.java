package org.wshuai.leetcode;

/**
 * Created by Wei on 04/26/2020.
 * #1422 https://leetcode.com/problems/maximum-score-after-splitting-a-string/
 */
public class MaximumScoreAfterSplittingAString {
	// time O(n), space O(1)
	public int maxScore(String s) {
		int res = 0, ones = 0, n = s.length();
		int[] zeros = new int[n];
		for(int i = 1; i < n; i++){
			zeros[i] = zeros[i - 1] + 1 - (s.charAt(i - 1) - '0');
		}
		for(int i = n - 1; i >= 1; i--){
			ones += (s.charAt(i) - '0');
			res = Math.max(res, ones + zeros[i]);
		}
		return res;
	}
}
