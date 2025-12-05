package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2023.
 * #2134 https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/
 */
public class MinimumSwapsToGroupAllOnesTogetherII {

	// time O(n), space O(1)
	public int minSwaps(int[] nums) {
		int n = nums.length, res = n, ones = 0, count = 0;
		for (int num : nums) {
			ones += num;
		}
		for (int i = 0, j = 0; i < n; i++) {
			while (j - i < ones) {
				count += nums[j++ % n];
			}
			res = Math.min(res, ones - count);
			count -= nums[i];
		}
		return res;
	}

	// time O(n), space O(1)
	public int minSwapsFixedLengthSlidingWindow(int[] nums) {
		int n = nums.length, res = n, k = 0, count = 0;
		for (int num : nums) {
			k += num;
		}
		if (k == n || k == 0) {
			return 0;
		}
		// For circular array we need to extend the possible ends
		// of window to n + k - 2
		// e.g. [1,1,0,0,1], all valid sliding windows are:
		// [1,1,0] [1,0,0] [0,0,1] [0,1,1] [1,1,1]
		// Represent with indexes:
		// [0,1,2] [1,2,3] [2,3,4] [3,4,5] [4,5,6]
		// Note we should not consider from [5,6,7] since they are
		// actually map back to [0,1,2] and repeat the sequence again
		for (int i = 0; i < n + k - 1; i++) {
			count += nums[i % n];
			if (i - k + 1 < 0) {
				continue;
			}
			res = Math.min(res, k - count);
			// Note that the max value for i - k - 1 is 4 so we don't
			// need to mod it
			count -= nums[i - k + 1];
		}
		return res;
	}
}
