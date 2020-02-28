package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 11/12/2019.
 * #0469 https://leetcode.com/problems/convex-polygon/
 */
public class ConvexPolygon {
	// time O(n)
	public boolean isConvex(List<List<Integer>> points) {
		int flag = 0;
		for(int i = 0; i < points.size(); i++){
			int cur = orientation(points, i);
			if(cur == 0){
				continue;
			}
			if(flag == 0){
				flag = cur > 0 ? 1 : -1;
			}else if(flag > 0 != cur > 0){
				return false;
			}
		}
		return true;
	}

	// https://www.geeksforgeeks.org/orientation-3-ordered-points/
	private int orientation(List<List<Integer>> points, int i){
		int n = points.size();
		List<Integer> p1 = points.get(i);
		List<Integer> p2 = points.get((i + 1) % n);
		List<Integer> p3 = points.get((i + 2) % n);
		int x1 = p1.get(0), y1 = p1.get(1), x2 = p2.get(0), y2 = p2.get(1), x3 = p3.get(0), y3 = p3.get(1);
		return (y2 - y1) * (x3 - x2) - (x2 - x1) * (y3 - y2);
	}
}
