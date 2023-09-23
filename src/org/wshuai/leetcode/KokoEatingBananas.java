package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2019.
 * #0875 https://leetcode.com/problems/koko-eating-bananas/
 */
public class KokoEatingBananas {

	// time O(log(max)), space O(1)
	public int minEatingSpeed(int[] piles, int h) {
		int low = 1, high = 0;
		for (int pile : piles) {
			high = Math.max(high, pile);
		}
		while (low < high) {
			int mid = (low + high) >> 1;
			if (canEatAll(piles, h, mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private boolean canEatAll(int[] piles, int h, int threshold) {
		int hours = 0;
		for (int pile : piles) {
			hours += (pile + threshold - 1) / threshold;
			if (hours > h) {
				return false;
			}
		}
		return true;
	}
}
