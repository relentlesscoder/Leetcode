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
	// follow up questions - https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/282372/Java-solution-with-all-3-follow-up-questions
	public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> intersect = new ArrayList<>();
		for(int num : nums1){
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		for(int num : nums2){
			if(map.containsKey(num) && map.get(num) > 0){
				intersect.add(num);
				map.put(num, map.get(num) - 1);
			}
		}
		int[] res = new int[intersect.size()];
		int i = 0;
		for(int num : intersect){
			res[i++] = num;
		}
		return res;
	}
}
