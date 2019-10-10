package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 9/8/16.
 */
public class Triangle {
	public static int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null || triangle.size() == 0) {
			return 0;
		}
		int len = triangle.size();
		int[] res = new int[len];
		for (int i = 0; i < len; i++) {
			List<Integer> curr = triangle.get(i);
			int cLen = curr.size();
			if (cLen == 1) {
				res[0] = curr.get(0);
			} else {
				int ot = 0;
				int nt = 0;
				for (int j = 0; j < cLen; j++) {
					int val = curr.get(j);
					if (j == 0) {
						ot = res[j];
						res[j] = ot + val;
					} else if (j == cLen - 1) {
						res[j] = ot + val;
					} else {
						nt = res[j];
						res[j] = val + (ot < nt ? ot : nt);
						ot = nt;
					}
				}
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			min = res[i] < min ? res[i] : min;
		}

		return min;
	}
}
