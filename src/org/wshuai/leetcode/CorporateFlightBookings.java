package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2019.
 * #1109 https://leetcode.com/problems/corporate-flight-bookings/
 */
public class CorporateFlightBookings {

	// time O(n), space O(n)
	public int[] corpFlightBookings(int[][] bookings, int n) {
		int[] res = new int[n];
		for (int[] booking : bookings) {
			res[booking[0] - 1] += booking[2];
			if (booking[1] < n) {
				res[booking[1]] -= booking[2];
			}
		}
		int seats = 0;
		for (int i = 0; i < n; i++) {
			seats += res[i];
			res[i] = seats;
		}
		return res;
	}
}
