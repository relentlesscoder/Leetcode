package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 07/19/17.
 * #0296 https://leetcode.com/problems/best-meeting-point/
 */
public class BestMeetingPoint {
	// time O(r*c)
	// http://www.cnblogs.com/grandyang/p/5291058.html
	public int minTotalDistance(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0){
			return 0;
		}
		int r = grid.length, c = grid[0].length;
		List<Integer> xs = new ArrayList<>(), ys = new ArrayList<>();
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(grid[i][j] == 1){
					xs.add(i);
					ys.add(j);
				}
			}
		}
		int res = 0, i = 0, j = xs.size() - 1;
		Collections.sort(xs);
		Collections.sort(ys);
		while(i < j){
			res += xs.get(j) - xs.get(i) + ys.get(j--) - ys.get(i++);
		}
		return res;
	}
}
