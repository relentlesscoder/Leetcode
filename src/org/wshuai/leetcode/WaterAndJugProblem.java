package org.wshuai.leetcode;

/**
 * Created by Wei on 3/21/17.
 * #365 https://leetcode.com/problems/water-and-jug-problem/
 */
public class WaterAndJugProblem {
	public boolean canMeasureWater(int x, int y, int z) {
		if (x + y < z) {
			return false;
		}
		if (x == z || y == z || x + y == z) {
			return true;
		}
		return z % gcd(x, y) == 0;
	}

	private int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
}
