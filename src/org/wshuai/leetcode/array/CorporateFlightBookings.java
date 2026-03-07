package org.wshuai.leetcode.array;

/**
 * Created by Wei on 10/02/2019.
 * #1109 https://leetcode.com/problems/corporate-flight-bookings/
 */
public class CorporateFlightBookings {

	// time O(n), space O(n)
	public int[] corpFlightBookings(int[][] bookings, int n) {
		// 差分数组应用
		int[] res = new int[n];
		int[] diff = new int[n + 1];
		for (int[] b : bookings) {
			diff[b[0] - 1] += b[2];
			diff[b[1]] -= b[2];
		}
		for (int i = 0, seats = 0; i < n; i++) {
			seats += diff[i];
			res[i] = seats;
		}
		return res;
	}
}
