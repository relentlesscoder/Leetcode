package org.wshuai.leetcode;

import java.util.TreeSet;

/**
 * Created by Wei on 12/30/2019.
 * #683 https://leetcode.com/problems/k-empty-slots/
 */
public class KEmptySlots {
	public int kEmptySlots(int[] bulbs, int K) {
		TreeSet<Integer> set = new TreeSet<>();
		for(int i = 0; i < bulbs.length; i++){
			Integer h = set.higher(bulbs[i]);
			Integer l = set.lower(bulbs[i]);
			if(h != null && h.intValue() - bulbs[i] - 1 == K){
				return i + 1;
			}
			if(l != null && bulbs[i] - l.intValue() - 1 == K){
				return i + 1;
			}
			set.add(bulbs[i]);
		}
		return -1;
	}
}
