package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2023.
 * #2216 https://leetcode.com/problems/minimum-deletions-to-make-array-beautiful/
 */
public class MinimumDeletionsToMakeArrayBeautiful {

	// time O(n), space O(1)
	public int minDeletion(int[] nums) {
		int n = nums.length, deletions = 0;
		for (int i = 0; i < n - 1; i++) {
			int currentIndexAfterShift = i - deletions; // calculate the new index
			if (currentIndexAfterShift % 2 == 0 && nums[i] == nums[i + 1]) {
				deletions++;
			}
		}
		// if the array after deletion is odd, one more character needs to be deleted to make it beautiful
		return deletions + ((n - deletions) % 2 == 0 ? 0 : 1);
	}

}
