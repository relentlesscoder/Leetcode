package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/12/2019.
 * #0822 https://leetcode.com/problems/card-flipping-game/
 */
public class CardFlippingGame {
	// time O(n)
	// https://leetcode.com/problems/card-flipping-game/discuss/125791/C%2B%2BJavaPython-Easy-and-Concise-with-Explanation
	public int flipgame(int[] fronts, int[] backs) {
		int res = Integer.MAX_VALUE;
		Set<Integer> invalid = new HashSet<>();
		for(int i = 0; i < fronts.length; i++){
			if(fronts[i] == backs[i]){
				invalid.add(fronts[i]);
			}
		}
		for(int f : fronts){
			if(invalid.contains(f)){
				continue;
			}
			res = Math.min(res, f);
		}
		for(int b : backs){
			if(invalid.contains(b)){
				continue;
			}
			res = Math.min(res, b);
		}
		return res == Integer.MAX_VALUE ? 0 : res;
	}
}
