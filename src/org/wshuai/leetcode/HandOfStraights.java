package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/16/2019.
 * #846 https://leetcode.com/problems/hand-of-straights/
 */
public class HandOfStraights {

	// sorting
	public boolean isNStraightHand(int[] hand, int W) {
		int len = hand.length;
		if(len % W == 1){
			return false;
		}
		int num = len / W;
		Arrays.sort(hand);
		int[][] res = new int[num][2];
		for(int i = 0; i < num; i++){
			res[i] = new int[]{0, 0};
		}
		for(int i = 0; i < hand.length; i++){
			boolean inserted = false;
			for(int[] lst: res){
				if(lst[1] == W){
					continue;
				}
				if(lst[1] == 0 || lst[0] == hand[i] - 1){
					lst[0] = hand[i];
					lst[1]++;
					inserted = true;
					break;
				}
			}
			if(!inserted){
				return false;
			}
		}
		return true;
	}
}
