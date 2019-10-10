package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 8/19/19.
 * #976 https://leetcode.com/problems/largest-perimeter-triangle/
 */
public class LargestPerimeterTriangle {
	public int largestPerimeter(int[] A) {
		Arrays.sort(A);
		for (int i = A.length - 3; i >= 0; i--) {
			if (A[i] + A[i + 1] > A[i + 2]) {
				return A[i] + A[i + 1] + A[i + 2];
			}
		}
		return 0;
	}
}
