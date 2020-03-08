package org.wshuai.leetcode;

import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Wei on 10/29/2019.
 * #0497 https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/
 */
public class RandomPointInNonOverlappingRectangles {
	private TreeMap<Integer, Integer> map;
	private int[][] rects;
	private int sum;
	private Random rnd;

	public RandomPointInNonOverlappingRectangles(int[][] rects) {
		rnd = new Random();
		this.rects = rects;
		map = new TreeMap<>();
		sum = 0;
		for(int i = 0; i < rects.length; i++){
			int[] rect = rects[i];
			sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
			map.put(sum, i);
		}
	}

	public int[] pick() {
		int randInt = rnd.nextInt(sum);
		int key = map.higherKey(randInt);
		int[] rect = rects[map.get(key)];
		int x = rect[0] + (key - randInt - 1) % (rect[2] - rect[0] + 1);
		int y = rect[1] + (key - randInt - 1) / (rect[2] - rect[0] + 1);
		return new int[]{x, y};
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */