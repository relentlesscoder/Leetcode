package org.wshuai.leetcode;

/**
 * Created by Wei on 12/24/2019.
 * #0810 https://leetcode.com/problems/chalkboard-xor-game/
 */
public class ChalkboardXORGame {
    // https://leetcode.com/problems/chalkboard-xor-game/discuss/165396/Detailed-math-explanation-Easy-to-understand
    public boolean xorGame(int[] nums) {
		/*
		Short explanation:
		If on Alice's first turn, there are even amount of numbers left, then on any Alice's turn, there will be even amount of numbers left, nothing can change that.
				The only way to make your opponent lose on the immediate next turn, is to let the them face ODD amount of the SAME number. Because
		2.1 If the numbers are not all the same, then the opponent can always choose between two numbers the one that won't lose immediately.
		2.2 If the number are all the same but the amount is even, then the opponent already wins.
		Combining 1 and 2, we know that whatever Bob does, Alice can always do something on the next turn and not lose. => Alice wins.
		*/
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        return xor == 0 || nums.length % 2 == 0;
    }
}
