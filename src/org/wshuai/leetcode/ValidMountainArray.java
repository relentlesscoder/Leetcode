package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/19.
 * #941 https://leetcode.com/problems/valid-mountain-array/
 */
public class ValidMountainArray {
	public boolean validMountainArray(int[] A) {
		if (A.length < 3) {
			return false;
		}
		int l = 0;
		int r = A.length - 1;
		while (l < A.length - 1 && A[l] < A[l + 1]) {
			l++;
		}
		while (r > 0 && A[r] < A[r - 1]) {
			r--;
		}
		return l == r && l != 0 && r != A.length - 1;
	}
}
