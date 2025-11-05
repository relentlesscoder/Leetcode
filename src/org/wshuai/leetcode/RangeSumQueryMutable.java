package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2016.
 * #0307 https://leetcode.com/problems/range-sum-query-mutable/
 */
public class RangeSumQueryMutable {

	// time O(m * log(n)), space O(n)
	class NumArrayBinaryIndexedTree {
		private BIT bit;
		private int[] arr;

		// time O(n), space O(n)
		public NumArrayBinaryIndexedTree(int[] nums) {
			arr = nums;
			/** O(n*log(n)) time initialization
			bit = new BIT(n);
			for (int i = 0; i < n; i++) {
				bit.update(i + 1, nums[i]);
			}*/
			bit = new BIT(nums);
		}

		// time O(log(n)), space O(1)
		public void update(int index, int val) {
			int delta = val - arr[index];
			arr[index] = val;
			bit.update(index + 1, delta);
		}

		// time O(log(n)), space O(1)
		public int sumRange(int left, int right) {
			return bit.query(right + 1) - bit.query(left);
		}

		private static class BIT {

			private int[] tree;

			public BIT(int n) {
				tree = new int[n + 1];
			}

			public BIT(int[] nums) {
				int n = nums.length;
				tree = new int[n + 1];
				for (int i = 1; i <= n; i++) { // Linear time initialization
					tree[i] += nums[i - 1];
					int index = i + (i & -i);
					if (index <= n) {
						tree[index] += tree[i];
					}
				}
			}

			public void update(int index, int val) {
				while (index < tree.length) {
					tree[index] += val;
					index += index & -index;
				}
			}

			public int query(int index) {
				int res = 0;
				while (index > 0) {
					res += tree[index];
					index -= index & -index;
				}
				return res;
			}
		}
	}

	// time O(m * log(n)), space O(n)
	class NumArraySegmentTree {

		private int n;

		private SegmentTree st;

		private int[] arr;

		// time O(n), space O(n)
		public NumArraySegmentTree(int[] nums) {
			arr = nums;
			n = nums.length;
			st = new SegmentTree(nums);
		}

		// time O(log(n)), space O(1)
		public void update(int index, int val) {
			arr[index] = val;
			st.update(index, val);
		}

		// time O(log(n)), space O(1)
		public int sumRange(int left, int right) {
			return st.query(left, right);
		}

		private static class SegmentTree {

			private int n;
			private int[] tree;

			public SegmentTree(int[] nums) {
				n = nums.length;
				tree = new int[4 * n];
				build(nums, 1, 0, n - 1);
			}

			public void update(int index, int val) {
				update(1, 0, n - 1, index, val);
			}

			public int query(int start, int end) {
				return query(1, 0, n - 1, start, end);
			}

			private int query(int node, int left, int right, int start, int end) {
				if (left >= start && right <= end) {
					return tree[node];
				}
				int mid = (left + right) / 2;
				if (end <= mid) {
					return query(node * 2, left, mid, start, end);
				}
				if (start > mid) {
					return query(node * 2 + 1, mid + 1, right, start, end);
				}
				int leftRes = query(node * 2, left, mid, start, end);
				int rightRes = query(node * 2 + 1, mid + 1, right, start, end);
				return leftRes + rightRes;
			}

			private void update(int node, int left, int right, int index, int val) {
				if (left == right) {
					tree[node] = val;
					return;
				}
				int mid = (left + right) / 2;
				if (index <= mid) {
					update(node * 2, left, mid, index, val);
				} else {
					update(node * 2 + 1, mid + 1, right, index, val);
				}
				maintain(node);
			}

			private void build(int[] nums, int node, int left, int right) {
				if (left == right) {
					tree[node] = nums[left];
					return;
				}
				int mid = (left + right) / 2;
				build(nums, node * 2, left, mid);
				build(nums, node * 2 + 1, mid + 1, right);
				maintain(node);
			}

			private void maintain(int node) {
				tree[node] = tree[node * 2] + tree[node * 2 + 1];
			}
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

