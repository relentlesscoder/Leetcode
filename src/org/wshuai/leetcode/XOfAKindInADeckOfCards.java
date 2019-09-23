package org.wshuai.leetcode;

/**
 * Created by Wei on 8/30/2019.
 * #914 https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/
 */
public class XOfAKindInADeckOfCards {
	public boolean hasGroupsSizeX(int[] deck) {
		int[] count = new int[10000];
		for (int c : deck) {
			count[c]++;
		}

		int g = -1;
		for (int i = 0; i < 10000; i++) {
			if (count[i] > 0) {
				if (g == -1) {
					g = count[i];
				} else {
					g = gcd(g, count[i]);
				}
			}
		}

		return g >= 2;
	}

	// greatest common divisor
	public int gcd(int x, int y) {
		return x == 0 ? y : gcd(y % x, x);
	}
}
