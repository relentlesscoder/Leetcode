package org.wshuai.leetcode;

/**
 * Created by Wei on 03/21/2017.
 * #0365 https://leetcode.com/problems/water-and-jug-problem/
 */
public class WaterAndJugProblem {
	public boolean canMeasureWater(int x, int y, int z) {
		if(x + y < z){
			return false;
		}
		if(x == z || y == z || x + y == z){
			return true;
		}
		return z % gcd(x, y) == 0;
	}

	private int gcd(int x, int y){
		return x == 0 ? y : gcd(y % x, x);
	}
}
