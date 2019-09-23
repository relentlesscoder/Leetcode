package org.wshuai.leetcode;

import java.util.Random;

/**
 * Created by Wei on 9/18/2019.
 * #528 https://leetcode.com/problems/random-pick-with-weight/
 */
public class RandomPickWithWeight {
	private int sum;
	private int[] w;
	private Random random;

	public RandomPickWithWeight(int[] w) {
		random = new Random();
		this.w = w;
		for (int weight : w) {
			sum += weight;
		}
	}

	public int pickIndex() {
		int idx = random.nextInt(sum);
		idx++;
		int i = 0;
		for (; i < w.length; i++) {
			idx -= w[i];
			if (idx <= 0) {
				break;
			}
		}
		return i;
	}
}
