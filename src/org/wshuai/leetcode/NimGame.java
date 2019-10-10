package org.wshuai.leetcode;

/**
 * Created by Wei on 9/18/2016.
 * #292 https://leetcode.com/problems/nim-game/
 */
public class NimGame {
	public boolean canWinNim(int n) {
		return n % 4 != 0;
	}
}
