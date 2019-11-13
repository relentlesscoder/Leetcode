package org.wshuai.leetcode;

import java.util.HashSet;

/**
 * Created by Wei on 11/12/19.
 * #822 https://leetcode.com/problems/card-flipping-game/
 */
public class CardFlippingGame {
	public int flipgame(int[] fronts, int[] backs) {
		HashSet<Integer> same = new HashSet<>();
		for (int i = 0; i < fronts.length; ++i){
			if (fronts[i] == backs[i]){
				same.add(fronts[i]);
			}
		}
		int res = 3000;
		for (int i : fronts){
			if (!same.contains(i)){
				res = Math.min(res, i);
			}
		}
		for (int i : backs){
			if (!same.contains(i)){
				res = Math.min(res, i);
			}
		}
		return res % 3000;
	}
}
