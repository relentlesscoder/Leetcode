package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2023.
 * #2134 https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/
 */
public class MinimumSwapsToGroupAllOnesTogetherII {

	// time O(n), space O(1)
	// https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/solutions/1833560/java-o-n-explanation-with-diagrams/
	public int minSwaps(int[] nums) {
		int res = nums.length, n = nums.length, ones = 0, count = 0;
		for (int num : nums) {
			ones += num;
		}
		for (int i = 0, j = 0; i < n; i++) {
			while (j - i + 1 <= ones) {
				count += nums[j++ % n];
			}
			res = Math.min(res, ones - count);
			count -= nums[i];
		}
		return res;
	}
}
