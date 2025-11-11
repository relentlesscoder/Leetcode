package org.wshuai.algorithm.binaryIndexedTree;

/**
 * Created by Wei on 09/25/2016.
 */
public class BinaryIndexedTree {

	private final int[] tree;

	public BinaryIndexedTree(int n) {
		tree = new int[n + 1];
	}

	public void add(int index, int val) {
		while (index < tree.length) {
			tree[index] += val;
			index += index & -index;
		}
	}

	public int sum(int index) {
		int res = 0;
		while (index > 0) {
			res += tree[index];
			index -= index & -index;
		}
		return res;
	}

	public int query(int left, int right) {
		return sum(right) - sum(left - 1);
	}
}
