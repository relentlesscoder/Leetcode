package org.wshuai.leetcode;

/**
 * Created by Wei on 02/12/2020.
 * #1344 https://leetcode.com/problems/angle-between-hands-of-a-clock/
 */
public class AngleBetweenHandsOfAClock {

	// time O(1)
	public double angleClock(int hour, int minutes) {
		double h = minutes / 60.0 * 5.0 + (hour == 12 ? 0 : hour) * 5.0, m = (double)minutes;
		double angle = Math.abs(h - m) / 60.0 * 360.0;
		return Math.min(angle, 360 - angle);
	}
}
