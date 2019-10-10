package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/6/19.
 * #986 https://leetcode.com/problems/interval-list-intersections/
 */
public class IntervalListIntersections {

	public int[][] intervalIntersection(int[][] A, int[][] B) {
		int i = 0;
		int j = 0;

		List<int[]> res = new ArrayList<int[]>();
		while (i < A.length && j < B.length) {
			int low = Math.max(A[i][0], B[j][0]);
			int high = Math.min(A[i][1], B[j][1]);

			if (low <= high) {
				int[] arr = new int[2];
				arr[0] = low;
				arr[1] = high;
				res.add(arr);
			}

			// the one with smaller end can be removed
			// since there will be no further intersection for it
			if (A[i][1] < B[j][1]) {
				i++;
			} else {
				j++;
			}
		}

		int[][] val = new int[res.size()][2];
		for (int k = 0; k < res.size(); k++) {
			val[k] = res.get(k);
		}
		return val;
	}

}
