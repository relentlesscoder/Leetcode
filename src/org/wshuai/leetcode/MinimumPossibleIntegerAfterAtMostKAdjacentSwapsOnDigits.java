package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 07/28/2020.
 * #1505 https://leetcode.com/problems/minimum-possible-integer-after-at-most-k-adjacent-swaps-on-digits/
 */
public class MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits {

	// time O(n * log(n)), space O(n)
	public String minIntegerBinaryIndexedTree(String num, int k) {
		int n = num.length();
		Deque<Integer>[] queues = new ArrayDeque[10];
		Arrays.setAll(queues, i -> new ArrayDeque<>());
		for(int i = 0; i < n; i++) {
			// Enqueue indexes from the left to right
			queues[num.charAt(i) - '0'].offer(i);
		}
		StringBuilder res = new StringBuilder();
		BIT bit = new BIT(n);
		// For each index, try greedily swap with the least digit to make result smaller
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <= 9; j++) {
				if(queues[j].size() == 0){
					continue;
				}
				int index = queues[j].peek();
				// Find elements that are swapped from nums[index]'s right ([index, n - 1]) to it's left
				int shift = bit.query(index + 1, n);
				// Now index are shifted to index + shift, number of swaps needed is index + shift - i
				int distance = index + shift - i;
				if(distance <= k) { // Swap if possible
					res.append(j);
					bit.update(queues[j].poll() + 1); // Update in BIT
					k -= distance;
					break;
				}
			}
		}
		return res.toString();
	}

	private static class BIT {

		private int[] tree;

		public BIT(int n) {
			tree = new int[n + 1];
		}

		public int query(int left, int right) {
			return sum(right) - sum(left - 1);
		}

		public int sum(int index) {
			int res = 0;
			while (index > 0) {
				res += tree[index];
				index -= index & -index;
			}
			return res;
		}

		public void update(int index) {
			while (index < tree.length) {
				tree[index]++;
				index += index & -index;
			}
		}
	}

	// time O(n * log(n)), space O(n)
	public String minIntegerSegmentTree(String num, int k) {
		int n = num.length();
		Deque<Integer>[] queues = new ArrayDeque[10];
		Arrays.setAll(queues, i -> new ArrayDeque<>());
		for(int i = 0; i < n; i++) { // O(n)
			queues[num.charAt(i) - '0'].offer(i);
		}
		StringBuilder res = new StringBuilder();
		SegmentTree st = new SegmentTree(n);
		for(int i = 0; i < n; i++) { // O(n)
			for(int j = 0; j <= 9; j++) {
				if(queues[j].isEmpty()){
					continue;
				}
				int index = queues[j].peek();
				// Find elements that are swapped from nums[index]'s right ([index, n - 1]) to it's left
				int shift = st.query(index, n - 1);
				// Now index are shifted to index + shift, number of swaps needed is index + shift - i
				int distance = index + shift - i;
				if(distance <= k) { // Swap if possible
					res.append(j);
					st.update(queues[j].poll(), 1); // Update in BIT
					k -= distance;
					break;
				}
			}
		}
		return res.toString();
	}

	private static class SegmentTree {

		private int n;
		private int[] tree;

		public SegmentTree(int n) {
			this.n = n;
			tree = new int[4 * n];
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

		public void update(int index, int val) {
			update(1, 0, n - 1, index, val);
		}

		private void update(int node, int left, int right, int index, int val) {
			if (left == right) {
				tree[node] += val;
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

		private void maintain(int node) {
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
	}
}
