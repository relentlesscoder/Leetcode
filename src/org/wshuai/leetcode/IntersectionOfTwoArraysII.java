package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/19/2016.
 * #0350 https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class IntersectionOfTwoArraysII {
	// time O(n), space O(n)
	public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		for(int i : nums1){
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		for(int i : nums2){
			if(map.containsKey(i)){
				int d = map.get(i);
				if(d > 1){
					map.put(i, d - 1);
				}else{
					map.remove(i);
				}
				list.add(i);
			}
		}
		int[] res = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			res[i] = list.get(i);
		}
		return res;
	}
}
