package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/11/16.
 * #447 https://leetcode.com/problems/number-of-boomerangs/
 */
public class NumberOfBoomerangs {
	//O(n), a very low question
	public int numberOfBoomerangs(int[][] points) {
		if (points == null || points.length == 0) {
			return 0;
		}
		int num = 0;
		int len = points.length;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < len; i++) {
			map.clear();
			int x1 = points[i][0];
			int y1 = points[i][1];
			for (int j = 0; j < len; j++) {
				if (i != j) {
					int x2 = points[j][0];
					int y2 = points[j][1];
					int a = x1 - x2;
					int b = y1 - y2;
					int dis = a * a + b * b;
					if (map.containsKey(dis)) {
						int c = map.get(dis);
						map.put(dis, c + 1);
					} else {
						map.put(dis, 1);
					}
				}
			}
			for (int cnt : map.values()) {
				num += cnt * (cnt - 1);
			}
		}
		return num;
	}
}
