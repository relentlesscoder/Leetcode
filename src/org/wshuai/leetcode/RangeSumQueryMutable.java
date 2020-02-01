package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2016.
 * #0307 https://leetcode.com/problems/range-sum-query-mutable/
 */
public class RangeSumQueryMutable {
	private int[] segmentTree;
	private int[] arr;
	private int n;

	public RangeSumQueryMutable(int[] nums) {
		n = nums.length;
		if (n > 0) {
			arr = nums;
			int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
			int size = 2 * (int) Math.pow(2, x) - 1;
			segmentTree = new int[size];
			build(0, n - 1, 0);
		}
	}

	public void update(int i, int val) {
		if (i < 0 || i >= n) {
			return;
		}
		int diff = val - arr[i];
		arr[i] = val;
		updateUtil(0, n - 1, i, diff, 0);
	}

	public int sumRange(int i, int j) {
		if (i < 0 || j >= n || i > j) {
			return -1;
		}
		return sumRangeUtil(0, n - 1, i, j, 0);
	}

	private int build(int start, int end, int index) {
		if (start == end) {
			segmentTree[index] = arr[start];
			return arr[start];
		}
		int mid = start + (end - start) / 2;
		segmentTree[index] = build(start, mid, index * 2 + 1)
			+ build(mid + 1, end, index * 2 + 2);
		return segmentTree[index];
	}

	private void updateUtil(int start, int end, int i, int diff, int index) {
		if (i < start || i > end) {
			return;
		}
		segmentTree[index] = segmentTree[index] + diff;
		if (start != end) {
			int mid = start + (end - start) / 2;
			updateUtil(start, mid, i, diff, 2 * index + 1);
			updateUtil(mid + 1, end, i, diff, 2 * index + 2);
		}
	}

	private int sumRangeUtil(int start, int end, int i, int j, int index) {
		if (i <= start && j >= end) {
			return segmentTree[index];
		}
		if (i > end || j < start) {
			return 0;
		}
		int mid = start + (end - start) / 2;
		return sumRangeUtil(start, mid, i, j, 2 * index + 1)
			+ sumRangeUtil(mid + 1, end, i, j, 2 * index + 2);
	}
}

/* Binary Index Tree solution

class RangeSumQueryMutable {
	private int[] bit;
	private int[] nums;
	private int n;

	public RangeSumQueryMutable(int[] nums) {
		n = nums.length;
		if (n > 0) {
			this.nums = nums;
			bit = new int[n + 1];
			build();
		}
	}

	public void update(int i, int val) {
		int diff = val - nums[i];
		nums[i] = val;
		updateUtil(i, diff);
	}

	public int sumRange(int i, int j) {
		return sumUtil(j) - sumUtil(i - 1);
	}

	private int sumUtil(int index) {
		int sum = 0;
		index++;
		while (index > 0) {
			sum += bit[index];
			index -= (index & -index);
		}
		return sum;
	}

	private void updateUtil(int index, int val) {
		index++;
		while (index <= n) {
			bit[index] += val;
			index += (index & -index);
		}
	}

	private void build() {
		for (int i = 0; i < n; i++) {
			updateUtil(i, nums[i]);
		}
	}
}*/
