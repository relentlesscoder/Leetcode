package org.wshuai.leetcode;

/**
 * Created by Wei on 11/8/19.
 * #478 https://leetcode.com/problems/generate-random-point-in-a-circle/
 */
public class GenerateRandomPointInACircle {
	private double rad, xc, yc;

	public GenerateRandomPointInACircle(double radius, double x_center, double y_center) {
		rad = radius;
		xc = x_center;
		yc = y_center;
	}

	public double[] randPoint() {
		// get the bottom left corner of the square
		double x0 = xc - rad;
		double y0 = yc - rad;

		while(true){
			double x = x0 + Math.random() * rad * 2;
			double y = y0 + Math.random() * rad * 2;
			if((x - xc) * (x - xc) + (y - yc) * (y - yc) <= rad * rad){
				return new double[]{x, y};
			}
		}
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */
