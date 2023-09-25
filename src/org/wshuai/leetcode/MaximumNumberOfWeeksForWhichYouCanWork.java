package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2023.
 * #1953 https://leetcode.com/problems/maximum-number-of-weeks-for-which-you-can-work/
 */
public class MaximumNumberOfWeeksForWhichYouCanWork {

	// time O(n), space O(1)
	public long numberOfWeeks(int[] milestones) {
		long sum = 0, max = 0, sumOfTheRest = 0;
		for (int m : milestones) {
			sum += m;
			max = Math.max(max, m);
		}
		// https://leetcode.com/problems/maximum-number-of-weeks-for-which-you-can-work/solutions/1375381/c-max-element-solution-greedy-with-proof/
		sumOfTheRest = sum - max;
		if (sumOfTheRest >= max) {
			return sum;
		} else {
			return 2 * sumOfTheRest + 1;
		}
	}
}
