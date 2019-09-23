package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 7/19/17.
 * #296 https://leetcode.com/problems/best-meeting-point/
 */
public class BestMeetingPoint {
	// http://www.cnblogs.com/grandyang/p/5291058.html
	public int minTotalDistance(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int r = grid.length;
		int c = grid[0].length;
		List<Integer> rows = new ArrayList<Integer>();
		List<Integer> cols = new ArrayList<Integer>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (grid[i][j] == 1) {
					rows.add(i);
					cols.add(j);
				}
			}
		}
		Collections.sort(rows);
		Collections.sort(cols);
		int i = 0;
		int j = rows.size() - 1;
		int min = 0;
		while (i < j) {
			min += rows.get(j) - rows.get(i) + cols.get(j) - cols.get(i);
			j--;
			i++;
		}
		return min;
	}
}
