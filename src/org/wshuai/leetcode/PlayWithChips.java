package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/2019.
 * #1217 https://leetcode.com/problems/play-with-chips/
 */
public class PlayWithChips {
	public int minCostToMoveChips(int[] chips) {
		int even = 0;
		int odd = 0;
		for(int c: chips){
			if(c % 2 == 0){
				even++;
			}else{
				odd++;
			}
		}
		// since move 2 units cost 0, any chip at any even (odd)
		// index moves to any other even (odd) index cost 0. Move a chip
		// from even (odd) to any other odd (even) index cost 1.
		// So the minimum cost is min(even, odd).
		return Math.min(even, odd);
	}
}
