package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/21/19.
 * #982 https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/
 */
public class TriplesWithBitwiseAndEqualToZero {
	public int countTriplets(int[] A) {
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for(int a : A){
			for(int b : A){
				int x = a & b;
				map.put(x, map.getOrDefault(x, 0) + 1);
			}
		}
		for(int a : A){
			for(Map.Entry<Integer, Integer> entry : map.entrySet()){
				int k = entry.getKey();
				if((a & k) == 0){
					res += entry.getValue();
				}
			}
		}
		return res;
	}
}
