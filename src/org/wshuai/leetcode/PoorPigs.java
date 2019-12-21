package org.wshuai.leetcode;

/**
 * Created by Wei on 12/21/2019.
 * #458 https://leetcode.com/problems/poor-pigs/
 */
public class PoorPigs {
	// yet another stupid question
	// https://leetcode.com/problems/poor-pigs/discuss/94266/Another-explanation-and-solution
	public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
		int pigs = 0;
		while (Math.pow(minutesToTest / minutesToDie + 1, pigs) < buckets) {
			pigs += 1;
		}
		return pigs;
	}
}
