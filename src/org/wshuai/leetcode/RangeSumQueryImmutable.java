package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0303 https://leetcode.com/problems/range-sum-query-immutable/
 */
public class RangeSumQueryImmutable {
	private int[] prefixSum;

	public RangeSumQueryImmutable(int[] nums) {
		int n = nums.length;
		prefixSum = new int[n + 1];
		for(int i = 1; i <= n; i++){
			prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
		}
	}

	public int sumRange(int i, int j) {
		return prefixSum[j + 1] - prefixSum[i];
	}
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);
