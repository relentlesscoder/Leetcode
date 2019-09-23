package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 7/18/17.
 * #575 https://leetcode.com/problems/distribute-candies/
 */
public class DistributeCandies {
	public int distributeCandies(int[] candies) {
		Set<Integer> kinds = new HashSet<Integer>();
		for (int candy : candies) {
			kinds.add(candy);
		}
		return kinds.size() >= candies.length / 2 ? candies.length / 2 : kinds.size();
	}
}
