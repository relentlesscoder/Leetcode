package org.wshuai.leetcode;

import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Wei on 09/18/2019.
 * #0528 https://leetcode.com/problems/random-pick-with-weight/
 */
public class RandomPickWithWeight {
	private int sum;
	private TreeMap<Integer, Integer> treeMap;
	private Random random;

	// time O(n*log(n))
	public RandomPickWithWeight(int[] w) {
		random = new Random();
		treeMap = new TreeMap<>();
		sum = 0;
		for(int i = 0; i < w.length; i++){
			sum += w[i];
			treeMap.put(sum, i);
		}
	}

	// time O(log(n))
	public int pickIndex() {
		int idx = random.nextInt(sum) + 1;
		int key = treeMap.ceilingKey(idx);
		return treeMap.get(key);
	}
}
