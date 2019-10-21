package org.wshuai.leetcode;

/**
 * Created by Wei on 10/18/2019.
 * #1227 https://leetcode.com/problems/airplane-seat-assignment-probability/
 */
public class AirplaneSeatAssignmentProbability {
	// see https://leetcode.com/problems/airplane-seat-assignment-probability/discuss/407582/1-line-O(1)-with-easy-induction
	public double nthPersonGetsNthSeat(int n) {
		return n == 1 ? 1 : 0.5;
	}
}
