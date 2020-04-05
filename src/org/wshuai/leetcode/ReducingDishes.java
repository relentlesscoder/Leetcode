package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 04/05/2020.
 * #1402 https://leetcode.com/problems/reducing-dishes/
 */
public class ReducingDishes {
	// time O(n*log(n))
	public int maxSatisfaction(int[] satisfaction) {
		int res = 0, n = satisfaction.length, total = 0;
		Arrays.sort(satisfaction);
		// add satisfaction[i] until it make the running sum negative
		for(int i = n - 1; i >= 0 && satisfaction[i] + total > 0; i--){
			total += satisfaction[i];
			// add running sum to the result since each time we add a new dish,
			// all the current dishes advance by 1 position to the right
			res += total;
		}
		return res;
	}
}
