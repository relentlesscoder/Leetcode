package org.wshuai.leetcode;

/**
 * Created by Wei on 10/2/2019.
 * #1109 https://leetcode.com/problems/corporate-flight-bookings/
 */
public class CorporateFlightBookings {
	// https://leetcode.com/problems/corporate-flight-bookings/discuss/328871/C%2B%2BJava-with-picture-O(n)
	public int[] corpFlightBookings(int[][] bookings, int n) {
		int[] res = new int[n];
		for(int[] b: bookings){
			res[b[0] - 1] += b[2];
			if(b[1] < n){
				res[b[1]] -= b[2];
			}
		}
		for(int i = 1; i < n; i++){
			res[i] += res[i - 1];
		}
		return res;
	}
}
