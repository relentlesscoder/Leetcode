package org.wshuai.algorithm.binaryIndexedTree;

/**
 * Created by Wei on 9/25/2016.
 */
public class BinaryIndexedTree {
	private int[] btree;
	private int[] arr;

	public BinaryIndexedTree(int[] nums) {
		if (nums == null) {
			throw new IllegalArgumentException("Invalid input.");
		}

		btree = new int[nums.length + 1];
		arr = nums;

		for (int i = 0; i < nums.length; i++) {
			add(i + 1, nums[i]);
		}
	}

	private void add(int i, int val) {
		for (int j = i; j < btree.length; j = j + (j & (-j))) {
			btree[j] += val;
		}
	}

	public void update(int i, int val) {
		if (validateIdx(i)) {
			add(i + 1, val - arr[i]);
			arr[i] = val;
		}
	}

	public int sumRange(int i, int j) {
		if (validateIdx(i) && validateIdx(j)) {
			return sumHelper(j + 1) - sumHelper(i);
		}
		return 0;
	}

	public int sumHelper(int i) {
		int sum = 0;
		for (int j = i; j >= 1; j = j - (j & (-j))) {
			sum += btree[j];
		}
		return sum;
	}

	private boolean validateIdx(int i) {
		return i >= 0 && i < arr.length;
	}
}
