package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/12/2023.
 * #2155 https://leetcode.com/problems/all-divisions-with-the-highest-score-of-a-binary-array/
 */
public class AllDivisionsWithTheHighestScoreOfABinaryArray {

	// time O(n), space O(1)
	public List<Integer> maxScoreIndices(int[] nums) {
		int n = nums.length, highestScore = 0, onesFromRight = 0;
		int[] scores = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			scores[i] = scores[i - 1] + (nums[i - 1] == 0 ? 1 : 0);
		}
		highestScore = scores[n];
		for (int i = n - 1; i >= 0; i--) {
			onesFromRight += nums[i];
			scores[i] += onesFromRight;
			highestScore = Math.max(highestScore, scores[i]);
		}
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			if (scores[i] == highestScore) {
				res.add(i);
			}
		}
		return res;
	}
}
