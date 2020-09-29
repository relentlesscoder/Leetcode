package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/10/2019.
 * #0939 https://leetcode.com/problems/minimum-area-rectangle/
 */
public class MinimumAreaRectangle {

	// time O(n^2), space O(n)
	public int minAreaRect(int[][] points) {
		Map<Integer, List<Integer>> pointsAtX = new TreeMap<>();
		Map<Integer, Integer> closestX = new HashMap<>();
		for(int[] p : points){
			pointsAtX.putIfAbsent(p[0], new ArrayList<>());
			pointsAtX.get(p[0]).add(p[1]);
		}
		int res = Integer.MAX_VALUE;
		for(int x : pointsAtX.keySet()){
			List<Integer> ys = pointsAtX.get(x);
			Collections.sort(ys);
			for(int i = 0; i < ys.size(); i++){
				for(int j = i + 1; j < ys.size(); j++){
					int y1 = ys.get(i), y2 = ys.get(j), code = y1 * 40_001 + y2;
					if(closestX.containsKey(code)){
						res = Math.min(res, (y2 - y1) * (x - closestX.get(code)));
					}
					closestX.put(code, x);
				}
			}
		}
		return res == Integer.MAX_VALUE ? 0 : res;
	}

	// time O(n^2), space O(n)
	public int minAreaRectSimple(int[][] points) {
		int min = Integer.MAX_VALUE;
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for(int[] p : points){
			map.putIfAbsent(p[0], new HashSet<>());
			map.get(p[0]).add(p[1]);
		}
		for(int[] p1 : points){
			for(int[] p2 : points){
				if(p1[0] == p2[0] || p1[1] == p2[1]){
					continue;
				}
				if(map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])){
					min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
				}
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
}
