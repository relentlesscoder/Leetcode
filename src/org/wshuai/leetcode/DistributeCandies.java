package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 07/18/2017.
 * #0575 https://leetcode.com/problems/distribute-candies/
 */
public class DistributeCandies {
	// time O(n), space O(n)
	public int distributeCandies(int[] candies) {
		int half = (candies.length >> 1);
		Set<Integer> distinct = new HashSet<>();
		for(int cand : candies){
			distinct.add(cand);
		}
		if(distinct.size() < half){
			return distinct.size();
		}
		return half;
	}
}
