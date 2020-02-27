package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/11/2016.
 * #0447 https://leetcode.com/problems/number-of-boomerangs/
 */
public class NumberOfBoomerangs {
	// time O(n^2)
	public int numberOfBoomerangs(int[][] points) {
		int res = 0, n = points.length;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			map.clear();
			int x1 = points[i][0], y1 = points[i][1];
			for(int j = 0; j < n; j++){
				if(i == j){
					continue;
				}
				int x2 = points[j][0], y2 = points[j][1];
				int a = x1 - x2, b = y1 - y2;
				int dist = a * a + b * b;
				map.put(dist, map.getOrDefault(dist, 0) + 1);
			}
			for(Map.Entry<Integer, Integer> entry : map.entrySet()){
				int count = entry.getValue();
				// choose two points out of points that has same distance to i (order matters)
				// permutation: n! / (n - r)!
				res += count * (count - 1);
			}
		}
		return res;
	}
}
