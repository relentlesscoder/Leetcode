package org.wshuai.leetcode;

/**
 * Created by Wei on 12/24/2019.
 * #810 https://leetcode.com/problems/chalkboard-xor-game/
 */
public class ChalkboardXORGame {
	// https://leetcode.com/problems/chalkboard-xor-game/discuss/165396/Detailed-math-explanation-Easy-to-understand
	public boolean xorGame(int[] nums) {
		int xor = 0;
		for(int n : nums){
			xor ^= n;
		}
		return xor == 0 || nums.length % 2 == 0;
	}
}
