package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Wei on 06/21/2020.
 * #1488 https://leetcode.com/problems/avoid-flood-in-the-city/
 */
public class AvoidFloodInTheCity {

	// time O(n*log(n)), space O(n)
	public int[] avoidFlood(int[] rains) {
		int n = rains.length;
		int[] res = new int[n];
		Arrays.fill(res, Integer.MAX_VALUE);
		Map<Integer, Integer> map = new HashMap<>();
		TreeSet<Integer> empty = new TreeSet<>();
		for(int i = 0; i < n; i++){
			if(rains[i] == 0){
				empty.add(i);
			}else{
				if(map.containsKey(rains[i])){
					Integer hi = empty.higher(map.get(rains[i]));
					if(hi == null){
						return new int[0];
					}
					empty.remove(hi);
					res[hi] = rains[i];
				}
				map.put(rains[i], i);
				res[i] = -1;
			}
		}
		for(int i = 0; i < n; i++){
			if(res[i] == Integer.MAX_VALUE){
				res[i] = 1;
			}
		}
		return res;
	}
}
