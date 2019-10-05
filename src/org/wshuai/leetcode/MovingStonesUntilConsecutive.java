package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/4/2019.
 * #1033 https://leetcode.com/problems/moving-stones-until-consecutive/
 */
public class MovingStonesUntilConsecutive {
	// https://leetcode.com/problems/moving-stones-until-consecutive/discuss/282836/C%2B%2BJava-4-lines
	public int[] numMovesStones(int a, int b, int c) {
		int[] s = { a, b, c };
		Arrays.sort(s);
		if (s[2] - s[0] == 2){
			return new int[] { 0, 0 };
		}
		int min = 2;
		if(Math.min(s[1] - s[0], s[2] - s[1]) <= 2){
			min = 1;
		}
		return new int[] { min, s[2] - s[0] - 2 };
	}
}
