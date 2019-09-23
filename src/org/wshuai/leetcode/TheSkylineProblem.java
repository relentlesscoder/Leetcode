package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 6/28/17.
 * #218 https://leetcode.com/problems/the-skyline-problem/
 */
public class TheSkylineProblem {
	//See https://segmentfault.com/a/1190000003786782
	public List<int[]> getSkyline(int[][] buildings) {
		List<int[]> result = new ArrayList<int[]>();
		List<int[]> points = new ArrayList<int[]>();
		if (buildings == null || buildings.length == 0) {
			return result;
		}

		for (int[] building : buildings) {
			points.add(new int[]{building[0], -building[2]});
			points.add(new int[]{building[1], building[2]});
		}

		Collections.sort(points, new PointComparator());
		Queue<Integer> heights = new PriorityQueue<Integer>(10, Collections.reverseOrder());

		heights.offer(0);
		int prev = 0;
		for (int[] pt : points) {
			if (pt[1] < 0) {
				heights.offer(-pt[1]);
			} else {
				heights.remove(pt[1]);
			}

			int cur = heights.peek();
			if (cur != prev) {
				result.add(new int[]{pt[0], cur});
				prev = cur;
			}
		}
		return result;
	}

	class PointComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] a, int[] b) {
			if (a[0] != b[0]) {
				return a[0] - b[0];
			} else {
				return a[1] - b[1];
			}
		}
	}
}
