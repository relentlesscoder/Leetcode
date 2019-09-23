package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 7/23/17.
 * #599 https://leetcode.com/problems/minimum-index-sum-of-two-lists/
 */
public class MinimumIndexSumOfTwoLists {
	public String[] findRestaurant(String[] list1, String[] list2) {
		if ((list1 == null || list1.length == 0) || (list2 == null || list2.length == 0)) {
			return new String[0];
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < list1.length; i++) {
			map.put(list1[i], i);
		}
		int min = Integer.MAX_VALUE;
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < list2.length; i++) {
			if (!map.containsKey(list2[i])) {
				continue;
			}
			int sum = map.get(list2[i]) + i;
			if (sum <= min) {
				if (sum < min) {
					min = sum;
					res.clear();
				}
				res.add(list2[i]);
			}
		}
		return res.toArray(new String[0]);
	}
}
