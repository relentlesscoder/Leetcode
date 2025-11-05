package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Wei on 04/12/2020.
 * #1409 https://leetcode.com/problems/queries-on-a-permutation-with-key/
 */
public class QueriesOnAPermutationWithKey {

	// time O((m + n) * log(m + n)), space O(m + n)
	public int[] processQueries(int[] queries, int m) {
		int n = queries.length;
		int[] res = new int[n], map = new int[m + 1];
		// Use binary indexed tree to save number of elements
		// before each index i
		BIT bit = new BIT(m + n);
		// Add elements to the end of BIT
		for (int i = n; i < n + m; i++) { // O(m)
			bit.update(i + 1, 1); // log(m + n)
			// Map each index [1,2,...,m] to index in the BIT
			map[i - n + 1] = i;
		}
		for (int i = 0, head = n - 1; i < n; i++, head--) { // O(n)
			// Query BIT to get number count before map[queries[i]]
			res[i] = bit.query(map[queries[i]] + 1) - 1; // log(m + n)
			// Remove index map[queries[i]]
			bit.update(map[queries[i]] + 1, -1); // log(m + n)
			// Add index to the head
			bit.update(head + 1, 1); // log(m + n)
			// Remap index of queries[i] to current head
			map[queries[i]] = head;
		}
		return res;
	}

	private static class BIT {

		private int[] tree;

		public BIT(int n) {
			tree = new int[n + 1];
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

	// time O(n * log(m)), space O(m)
	public int[] processQueriesTreeSet(int[] queries, int m) {
		int n = queries.length, min = 0;
		int[] res = new int[n];
		Map<Integer, Integer> map = new HashMap<>();
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 1; i <= m; i++) {
			set.add(i);
		}
		// Each time a query is processed, remove the value and
		// add current min to the front of the tree set
		// For queries = [3,1,2,1] for array [1,2,3,4,5]
		// res[0] = 2; map: [3,0]; tree set: [0,1,2,4,5]; min = -1
		// res[1] = 1; map: [3,0], [1,-1]; tree set: [-1,0,2,4,5]; min = -2
		// res[2] = 2; map: [3,0], [1,-1], [2,-2]; tree set: [-2,-1,0,4,5]; min = -3
		// res[3] = 1; map: [3,0], [1,-3], [2,-2]; tree set: [-3,-2,0,4,5]; min = -4
		for (int i = 0; i < n; i++) {
			// Find the current mapped value for queries[i]
			int val = map.getOrDefault(queries[i], queries[i]);
			// Find the position for the mapped value
			res[i] = set.headSet(val).size();
			// Remove mapped value
			set.remove(val);
			// Remap the queries[i] to current min
			map.put(queries[i], min);
			// Add current min to the front and decrease min
			set.add(min--);
		}
		return res;
	}
}
