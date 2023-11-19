package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0303 https://leetcode.com/problems/range-sum-query-immutable/
 */
public class RangeSumQueryImmutable {

	private int[] prefix;

	// time O(n), space O(n)
	public RangeSumQueryImmutable(int[] nums) {
		int n = nums.length;
		prefix = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			prefix[i] = prefix[i - 1] + nums[i - 1];
		}
	}

	// time O(1), space O(1)
	public int sumRange(int left, int right) {
		return prefix[right + 1] - prefix[left];
	}

}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);
