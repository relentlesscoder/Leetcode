package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/2019.
 * #875 https://leetcode.com/problems/koko-eating-bananas/
 */
public class KokoEatingBananas {

	// same idea as #1011
	public int minEatingSpeed(int[] piles, int H) {
		int left = 1, right = 0;
		for(int p: piles){
			right = Math.max(p, right);
		}
		while(left < right){
			int mid = left + (right - left) / 2;
			if(canEatAll(piles, mid) > H){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

	private int canEatAll(int[] piles, int K){
		int hours = 0;
		for(int p: piles){
			hours += p / K;
			if(p % K != 0){
				hours++;
			}
		}
		return hours;
	}
}
