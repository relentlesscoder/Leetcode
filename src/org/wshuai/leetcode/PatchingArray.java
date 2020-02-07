package org.wshuai.leetcode;

/**
 * Created by Wei on 08/30/2017.
 * #0330 https://leetcode.com/problems/patching-array/
 */
public class PatchingArray {
	// time O(n)
	// https://discuss.leetcode.com/topic/35494/solution-explanation
	// https://www.cnblogs.com/grandyang/p/5165821.html
	public int minPatches(int[] nums, int n) {
		long miss = 1; // the least missing number from 0, [0, miss)
		int added = 0, i = 0;
		while (miss <= n) {
			// if current number is less or equal to miss,
			// we can simply extend the range to [0, miss + nums[i])
			if (i < nums.length && nums[i] <= miss) {
				miss += nums[i++];
			// if current number is greater than miss, we need to patch the array
			// we can do it greedily to get max possible range [0, miss + miss)
			} else {
				miss += miss;
				added++;
			}
		}
		return added;
	}
}
