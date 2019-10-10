package org.wshuai.leetcode;

/**
 * Created by Wei on 9/25/2016.
 * #378 https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestElementInASortedMatrix {

	//Binary search
	public int kthSmallestBS(int[][] matrix, int k) {
		if (matrix == null || k <= 0) {
			return -1;
		}
		int len = matrix.length;
		if (len == 0) {
			return -1;
		}
		int left = matrix[0][0];
		int right = matrix[len - 1][len - 1];
		while (left < right) {
			int mid = left + (right - left) / 2;
			int temp = 0;
			for (int i = 0; i < len; i++) {
				temp += binarySearch(matrix[i], len, mid);
			}
			if (temp < k) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	private int binarySearch(int[] nums, int right, int val) {
		int left = 0;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] <= val) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	public int kthSmallestNaive(int[][] matrix, int k) {
		if (matrix == null || k <= 0) {
			return -1;
		}
		int rLen = matrix.length;
		if (rLen == 0) {
			return -1;
		}
		int cLen = matrix[0].length;
		if (cLen == 0) {
			return -1;
		}
		if (k > rLen * cLen) {
			return -1;
		}
		int[] aux = new int[rLen];
		int kth = -1;
		for (; k > 0; k--) {
			kth = Integer.MAX_VALUE;
			int rIdx = -1;
			for (int i = 0; i < rLen; i++) {
				int val = aux[i];
				if (val < cLen && matrix[i][val] < kth) {
					kth = matrix[i][val];
					rIdx = i;
				}
			}
			aux[rIdx] = aux[rIdx] + 1;
		}
		return kth;
	}
}
