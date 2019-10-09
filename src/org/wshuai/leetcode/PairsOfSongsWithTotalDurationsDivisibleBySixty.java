package org.wshuai.leetcode;

/**
 * Created by Wei on 10/9/2019.
 * #1010 https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 */
public class PairsOfSongsWithTotalDurationsDivisibleBySixty {

	// best explanation - https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/discuss/296138/Java-solution-from-combination-perspective-with-best-explanation
	public int numPairsDivisibleBy60(int[] time) {
		int n = time.length;
		int[] map = new int[60];
		int res = 0;
		for(int i = 0; i < n; i++){
			int rem = time[i] % 60;
			map[rem]++;
		}
		res += map[0] * (map[0] - 1) / 2;
		res += map[30] * (map[30] - 1) / 2;
		for(int i = 1; i < 30; i++){
			res += map[i] * map[60 - i];
		}

		return res;
	}
}
