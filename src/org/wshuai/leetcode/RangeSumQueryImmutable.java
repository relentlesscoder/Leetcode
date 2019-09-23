package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #303 https://leetcode.com/problems/range-sum-query-immutable/
 */
public class RangeSumQueryImmutable {
	private int[] arr;
	private int len;

	public RangeSumQueryImmutable(int[] nums) {
		if (nums == null || nums.length == 0) {
			arr = null;
		} else {
			len = nums.length;
			arr = new int[len];
			arr[0] = nums[0];
			for (int i = 1; i < len; i++) {
				arr[i] = arr[i - 1] + nums[i];
			}
		}
	}

	public int sumRange(int i, int j) {
		if (i > j || i < 0 || j >= len) {
			throw new IllegalArgumentException("Invalid input.");
		}
		if (arr == null) {
			return -1;
		}
		if (i == 0) {
			return arr[j];
		}
		return arr[j] - arr[i - 1];
	}
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);
