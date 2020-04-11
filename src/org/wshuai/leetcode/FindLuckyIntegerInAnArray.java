package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/29/2020.
 * #1394 https://leetcode.com/problems/find-lucky-integer-in-an-array/
 */
public class FindLuckyIntegerInAnArray {
	// time O(n), space O(n)
	public int findLucky(int[] arr) {
		int res = Integer.MIN_VALUE;
		Map<Integer, Integer> map = new HashMap<>();
		for(int num : arr){
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		for(int key : map.keySet()){
			int val = map.get(key);
			if(key == val){
				res = Math.max(res, key);
			}
		}
		return res == Integer.MIN_VALUE ? -1 : res;
	}
}
