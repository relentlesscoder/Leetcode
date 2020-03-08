package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 01/26/2020.
 * #1333 https://leetcode.com/problems/filter-restaurants-by-vegan-friendly-price-and-distance/
 */
public class FilterRestaurantsByVeganFriendlyPriceAndDistance {
	// O(n*log(n))
	public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
		List<Integer> res = new ArrayList<>();
		List<int[]> filtered = new ArrayList<>();
		for(int[] re : restaurants){
			if((veganFriendly == 1 && re[2] != veganFriendly) || re[3] > maxPrice || re[4] > maxDistance){
				continue;
			}
			filtered.add(re);
		}
		Collections.sort(filtered, (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
		for(int[] f : filtered){
			res.add(f[0]);
		}
		return res;
	}
}
