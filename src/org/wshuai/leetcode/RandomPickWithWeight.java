package org.wshuai.leetcode;

import java.util.Random;

/**
 * Created by Wei on 09/18/2019.
 * #0528 https://leetcode.com/problems/random-pick-with-weight/
 */
public class RandomPickWithWeight {

	private int[] weight;
	private Random random;
	private int sum;

	public RandomPickWithWeight(int[] w) {
		random = new Random();
		for (int i = 1; i < w.length; i++) {
			w[i] += w[i - 1];
		}
		sum = w[w.length - 1];
		weight = w;
	}

	// time O(log(n))
	public int pickIndex() {
		int val = random.nextInt(sum) + 1;
		return binarySearch(weight, val);
	}

	private int binarySearch(int[] weight, int target) {
		int low = 0, high = weight.length - 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (weight[mid] < target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

	/** using tree map
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
	 // for [5, 2, 3, 1, 4], the map is
	 // 1 -> 5, 6 -> 7, 8 -> 8, 9 -> 11, 12 -> 15
	 int idx = random.nextInt(sum) + 1;
	 int key = treeMap.ceilingKey(idx);
	 return treeMap.get(key);
	 }**/
}
