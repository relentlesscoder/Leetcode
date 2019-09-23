package org.wshuai.leetcode;

/**
 * Created by Wei on 8/8/19.
 * #977 https://leetcode.com/problems/squares-of-a-sorted-array/
 */
public class SquaresOfASortedArray {
	public int[] sortedSquares(int[] A) {
		int[] res = new int[A.length];
		int left = 0;
		int right = A.length - 1;
		for (int i = A.length - 1; i >= 0; i--) {
			int val1 = A[left] * A[left];
			int val2 = A[right] * A[right];
			if (val1 >= val2) {
				res[i] = val1;
				left++;
			} else {
				res[i] = val2;
				right--;
			}
		}
		return res;
	}
}
