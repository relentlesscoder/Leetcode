package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2019.
 * #0478 https://leetcode.com/problems/generate-random-point-in-a-circle/
 */
public class GenerateRandomPointInACircle {
	private double radius, xc, yc, area, diameter;

	public GenerateRandomPointInACircle(double radius, double x_center, double y_center) {
		this.radius = radius;
		xc = x_center;
		yc = y_center;
		area = radius * radius;
		diameter = radius * 2;
	}

	public double[] randPoint() {
		double x0 = xc - radius;
		double y0 = yc - radius;

		while(true){
			double x = x0 + Math.random() * diameter;
			double y = y0 + Math.random() * diameter;
			if((x - xc) * (x - xc) + (y - yc) * (y - yc) <= area){
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
