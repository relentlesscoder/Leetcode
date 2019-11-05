package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Wei on 11/5/2019.
 * #519 https://leetcode.com/problems/random-flip-matrix/
 */
public class RandomFlipMatrix {
	private Map<Integer, Integer> map;

	private int rows, cols, total;

	private Random rand;

	public RandomFlipMatrix(int n_rows, int n_cols) {
		map = new HashMap<>();
		rand = new Random();
		rows = n_rows;
		cols = n_cols;
		total = rows * cols;
	}

	public int[] flip() {
		// get a value between 0 -> total - 1 uniformly at random
		int r = rand.nextInt(total--);
		// get the index mapped to the random value
		int x = map.getOrDefault(r, r);
		// remap r to the current tail index of the virtual array
		map.put(r, map.getOrDefault(total, total));
		// remap the current tail index of the virtual array to the index just swapped
		// no need to clear the map because of this step - all values now can be recycled
		map.put(total, x);
		return new int[]{x / cols, x % cols};
	}

	public void reset() {
		// no need to clear the map because all the index can be reused
		total = rows * cols;
	}
}
