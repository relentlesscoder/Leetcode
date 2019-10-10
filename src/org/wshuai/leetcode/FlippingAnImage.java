package org.wshuai.leetcode;

/**
 * Created by Wei on 8/7/19.
 * #832 https://leetcode.com/problems/flipping-an-image/
 */
public class FlippingAnImage {
	public int[][] flipAndInvertImage(int[][] A) {
		int len = A.length;
		int[][] res = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				res[j][i] = A[j][len - 1 - i];
				res[j][i] = res[j][i] == 1 ? 0 : 1;
			}
		}
		return res;
	}
}
