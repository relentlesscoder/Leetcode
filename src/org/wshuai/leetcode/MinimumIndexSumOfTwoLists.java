package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 07/23/2017.
 * #0599 https://leetcode.com/problems/minimum-index-sum-of-two-lists/
 */
public class MinimumIndexSumOfTwoLists {
	// time O(m+n), space O(m+n)
	public String[] findRestaurant(String[] list1, String[] list2) {
		int min = Integer.MAX_VALUE;
		List<String> res = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < list1.length; i++){
			map.put(list1[i], i);
		}
		for(int i = 0; i < list2.length; i++){
			if(!map.containsKey(list2[i])){
				continue;
			}
			int sum = i + map.get(list2[i]);
			if(sum <= min){
				if(sum < min){
					res = new ArrayList<>();
				}
				min = sum;
				res.add(list2[i]);
			}
		}
		return res.toArray(new String[res.size()]);
	}
}
