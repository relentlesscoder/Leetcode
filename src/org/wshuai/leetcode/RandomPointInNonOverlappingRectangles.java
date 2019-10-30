package org.wshuai.leetcode;

import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Wei on 10/29/2019.
 * #1182 https://leetcode.com/problems/shortest-distance-to-target-color/
 */
public class RandomPointInNonOverlappingRectangles {
	TreeMap<Integer, Integer> map;
	int[][] arrays;
	int sum;
	Random rnd;

	public RandomPointInNonOverlappingRectangles(int[][] rects) {
		rnd = new Random();
		arrays = rects;
		map = new TreeMap<>();
		sum = 0;

		for(int i = 0; i < rects.length; i++){
			int[] rect = rects[i];
			sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
			map.put(sum, i);
		}
	}

	public int[] pick() {
		int c = map.ceilingKey(rnd.nextInt(sum) + 1);
		return pickPoint(arrays[map.get(c)]);
	}

	private int[] pickPoint(int[] rect){
		int left = rect[0], right = rect[2], bottom = rect[1], top = rect[3];
		return new int[]{left + rnd.nextInt(right - left + 1), bottom + rnd.nextInt(top - bottom + 1)};
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */