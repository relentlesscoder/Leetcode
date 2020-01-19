package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/03/2016.
 * #0149 https://leetcode.com/problems/max-points-on-a-line/
 */
public class MaxPointsOnALine {
	// time O(n^2), space O(n)
	public int maxPoints(int[][] points) {
		if(points == null || points.length == 0 || points[0].length != 2){
			return 0;
		}
		int n = points.length;
		if(n <= 2){
			return n;
		}
		int max = 2;
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			int[] p1 = points[i];
			int same = 0, vertical = 0, cmax = 0;
			for(int j = i + 1; j < n; j++){
				int[] p2 = points[j];
				if(p1[0] == p2[0]){
					if(p1[1] == p2[1]){
						same++;
					}else{
						vertical++;
					}
				}else{
					int x = p2[1] - p1[1], y = p2[0] - p1[0], gcd = gcd(x, y);
					String slope = (x / gcd) + "_" + (y / gcd);
					map.put(slope, map.getOrDefault(slope, 0) + 1);
				}
			}
			for(int count : map.values()){
				cmax = Math.max(count, cmax);
			}
			cmax = Math.max(cmax, vertical);
			max = Math.max(cmax + same + 1, max);
			map.clear();
		}
		return max;
	}

	private int gcd(int x, int y){
		return x == 0 ? y : gcd(y % x, x);
	}
}
