package org.wshuai.leetcode;

import java.util.TreeSet;

/**
 * Created by Wei on 12/30/2019.
 * #0683 https://leetcode.com/problems/k-empty-slots/
 */
public class KEmptySlots {
	// time O(n*log(n)), space O(n)
	public int kEmptySlots(int[] bulbs, int K) {
		TreeSet<Integer> set = new TreeSet<>();
		for(int i = 0; i < bulbs.length; i++){
			int cur = bulbs[i];
			Integer high = set.higher(cur), low = set.lower(cur);
			if(high != null && high.intValue() - cur - 1 == K){
				return i + 1;
			}
			if(low != null && cur - low.intValue() - 1 == K){
				return i + 1;
			}
			set.add(cur);
		}
		return -1;
	}
}
