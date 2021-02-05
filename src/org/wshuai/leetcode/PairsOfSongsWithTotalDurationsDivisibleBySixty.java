package org.wshuai.leetcode;

/**
 * Created by Wei on 10/09/2019.
 * #1010 https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 */
public class PairsOfSongsWithTotalDurationsDivisibleBySixty {

	// time O(n)
	// https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/discuss/296138/Java-solution-from-combination-perspective-with-best-explanation
	public int numPairsDivisibleBy60(int[] time) {
		long res = 0;
		long[] reminders = new long[60];
		for(int t : time){
			reminders[t % 60]++;
		}
		// self pairing n!/r!*(n - r)!
		res += reminders[0] * (reminders[0] - 1) / 2; // 60 - 0 = 60
		res += reminders[30] * (reminders[30] - 1) / 2; // 60 - 30 = 30
		for(int i = 1; i < 30; i++){
			res += reminders[i] * reminders[60 - i];
		}
		return (int)res;
	}
}
