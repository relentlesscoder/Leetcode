package org.wshuai.leetcode;

/**
 * Created by Wei on 10/20/19.
 * #1232 https://leetcode.com/problems/check-if-it-is-a-straight-line/
 */
public class CheckIfItIsAStraightLine {

	// math check slope
	public boolean checkStraightLine(int[][] coordinates) {
		double slope = getSlope(coordinates[0], coordinates[1]);
		for(int i = 2; i < coordinates.length; i++){
			double curr = getSlope(coordinates[i], coordinates[i - 1]);
			if(curr != slope){
				return false;
			}
		}
		return true;
	}

	private double getSlope(int[] p, int[] q){
		return 1.0 * (p[1] - q[1]) / (p[0] - q[0]);
	}
}
