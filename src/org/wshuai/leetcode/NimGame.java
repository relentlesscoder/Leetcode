package org.wshuai.leetcode;

/**
 * Created by Wei on 09/18/2016.
 * #0292 https://leetcode.com/problems/nim-game/
 */
public class NimGame {
	// https://leetcode.com/problems/nim-game/discuss/73749/Theorem%3A-all-4s-shall-be-false
	public boolean canWinNim(int n) {
		return n % 4 != 0;
	}

	// TLE but makes more sense
	public boolean canWinNimDP(int n) {
		if(n <= 0)
			throw new IllegalArgumentException();
		if(n < 4)
			return true;
		boolean[] res = new boolean[n + 1];
		res[0] = true;
		res[1] = true;
		res[2] = true;
		res[3] = true;
		for(int i = 4 ; i <= n ; i++)
			res[i] = !(res[i - 1] && res[i - 2] && res[i - 3]);
		return res[n];
	}
}
