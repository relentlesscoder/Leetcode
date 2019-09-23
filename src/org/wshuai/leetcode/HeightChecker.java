package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 8/8/19.
 * #1051 https://leetcode.com/problems/height-checker/
 */
public class HeightChecker {
	public int heightChecker(int[] heights) {
		int[] aux = heights.clone();
		Arrays.sort(aux);
		int res = 0;
		for (int i = 0; i < heights.length; i++) {
			if (heights[i] != aux[i]) {
				res++;
			}
		}
		return res;
	}
}
