package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2016.
 * #0307 https://leetcode.com/problems/range-sum-query-mutable/
 */
public class RangeSumQueryMutable {

	// time O(m * log(n)), space O(n)
	private class NumArrayBinaryIndexedTree {

		private int n;
		private int[] bit, nums;

		// https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
		public NumArrayBinaryIndexedTree(int[] nums) {
			this.n = nums.length;
			this.bit = new int[n + 1];
			this.nums = nums;
			buildTree();
		}

		// time O(log(n))
		public void update(int index, int val) {
			int delta = val - nums[index];
			nums[index] = val;
			updateTree(index, delta);
		}

		// time O(log(n))
		public int sumRange(int left, int right) {
			return queryTree(right) - queryTree(left - 1);
		}

		private void buildTree() {
			for (int i = 0; i < n; i++) {
				updateTree(i, nums[i]);
			}
		}

		private void updateTree(int index, int value) {
			index++;
			while (index <= n) {
				bit[index] += value;
				index += (index & -index); // (index & -index) isolate the least significant non-zero bit
			}
		}

		private int queryTree(int index) {
			int res = 0;
			index++;
			while (index > 0) {
				res += bit[index];
				index -= (index & -index);
			}
			return res;
		}
	}

	// time O(m * log(n)), space O(n)
	private class NumArraySegmentTree {

		private int n;
		private int[] segmentTree, nums;

		// time O(n)
		public NumArraySegmentTree(int[] nums) {
			this.n = nums.length;
			this.nums = nums;
			// https://www.geeksforgeeks.org/relationship-number-nodes-height-binary-tree/
			int height = (int) Math.ceil(Math.log(n) / Math.log(2)); // height of segment tree
			int size = 2 * (int) Math.pow(2, height) - 1; // maximum size of segment tree
			this.segmentTree = new int[size];
			buildTree(0, n - 1, 0);
		}

		// time O(log(n))
		public void update(int index, int val) {
			if (index < 0 || index >= n) {
				return;
			}
			int delta = val - nums[index];
			nums[index] = val;
			updateTree(0, n - 1, index, delta, 0);
		}

		// time O(log(n))
		public int sumRange(int left, int right) {
			if (left < 0 || right >= n || left > right) {
				return 0;
			}
			return queryRange(0, n - 1, left, right, 0);
		}

		private int buildTree(int start, int end, int treeIndex) {
			if (start == end) {
				segmentTree[treeIndex] = nums[start];
				return nums[start];
			}
			int mid = start + (end - start) / 2;
			segmentTree[treeIndex] = buildTree(start, mid, treeIndex * 2 + 1) +
					buildTree(mid + 1, end, treeIndex * 2 + 2);
			return segmentTree[treeIndex];
		}

		private void updateTree(int start, int end, int index, int delta, int treeIndex) {
			if (index < start || index > end) {
				return;
			}
			segmentTree[treeIndex] += delta;
			if (start == end) {
				return;
			}
			int mid = start + (end - start) / 2;
			updateTree(start, mid, index, delta, treeIndex * 2 + 1);
			updateTree(mid + 1, end, index, delta, treeIndex * 2 + 2);

		}

		private int queryRange(int start, int end, int left, int right, int treeIndex) {
			if (left > end || right < start) { // the query range completely miss the current range
				return 0;
			}
			if (left <= start && right >= end) { // the query range fully covers the current range
				return segmentTree[treeIndex];
			}
			int mid = start + (end - start) / 2;
			return queryRange(start, mid, left, right, treeIndex * 2 + 1)
					+ queryRange(mid + 1, end, left, right, treeIndex * 2 + 2);
		}
	}

	// time O(m * sqrt(n)), space O(n)
	private class NumArraySquareRootDecomposition {

		private int size;
		private int[] nums, sum;

		// time O(n)
		public NumArraySquareRootDecomposition(int[] nums) {
			int n = nums.length, sqrt, bucket;
			this.nums = nums;
			sqrt = (int) Math.sqrt(n);
			this.size = n / sqrt;
			bucket = (n + sqrt - 1) / sqrt;
			this.sum = new int[bucket];
			for (int i = 0; i < n; i++) {
				this.sum[i / this.size] += nums[i];
			}
		}

		// time O(1)
		public void update(int index, int val) {
			int delta = val - nums[index], bucketIndex = index / this.size;
			nums[index] = val;
			sum[bucketIndex] += delta;
		}

		// time O(sqrt(n))
		public int sumRange(int left, int right) {
			int res = 0;
			while (left <= right && left % this.size != 0) {
				res += nums[left++];
			}
			while (left + this.size - 1 <= right) {
				res += sum[left / this.size];
				left += this.size;
			}
			while (left <= right) {
				res += nums[left++];
			}
			return res;
		}
	}
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */

