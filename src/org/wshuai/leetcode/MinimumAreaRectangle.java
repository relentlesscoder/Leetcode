package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/10/2019.
 * #939 https://leetcode.com/problems/minimum-area-rectangle/
 */
public class MinimumAreaRectangle {
	public int minAreaRect(int[][] points) {
		Map<Integer, List<Integer>> rows = new TreeMap<>();
		for(int[] point: points){
			int x = point[0], y = point[1];
			if(!rows.containsKey(x)){
				rows.put(x, new ArrayList<>());
			}
			rows.get(x).add(y);
		}

		int ans = Integer.MAX_VALUE;
		Map<Integer, Integer> lastx = new HashMap<>();
		for(int x: rows.keySet()){
			List<Integer> row = rows.get(x);
			Collections.sort(row);
			for(int i = 0; i < row.size(); i++){
				for(int j = i + 1; j < row.size(); j++){
					int y1 = row.get(i), y2 = row.get(j);
					// the other side should have the same code value
					// (1, 3) ---- (1, 6) -> 40_001 * 3 + 6
					// (3, 3) ---- (3, 6) -> 40_001 * 3 + 6
					int code = 40_001 * y1 + y2;
					if(lastx.containsKey(code)){
						ans = Math.min(ans, (x - lastx.get(code)) * (y2 - y1));
					}
					lastx.put(code, x);
				}
			}
		}
		return ans < Integer.MAX_VALUE ? ans : 0;
	}
}
