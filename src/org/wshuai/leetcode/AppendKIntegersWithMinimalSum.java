package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/21/2023.
 * #2195 https://leetcode.com/problems/append-k-integers-with-minimal-sum/
 */
public class AppendKIntegersWithMinimalSum {

	// time O(n * log(n)), space O(n)
	public long minimalKSum(int[] nums, int k) {
		long sum = 0;
		Arrays.sort(nums);
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (!set.contains(num) && num <= k) { // for each unique num <= k, find the next smallest value after k to replace it
				sum += num;
				k++;
			}
			set.add(num); // deduplicate
		}
		return 1L * k * (k + 1) / 2 - sum;
	}
}
