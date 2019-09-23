package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 4/8/17.
 * #526 https://leetcode.com/problems/beautiful-arrangement/
 */
public class BeautifulArrangement {
	//DFS
	public int countArrangement(int N) {
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for (int i = 1; i <= N; i++) {
			List<Integer> nums = new ArrayList<Integer>();
			for (int j = 1; j <= N; j++) {
				if (i % j == 0 || j % i == 0) {
					nums.add(j);
				}
			}
			if (nums.size() > 0) {
				map.put(i, nums);
			}
		}
		int len = map.size();
		if (len == 0) {
			return 0;
		}
		for (int i = 1; i <= map.size(); i++) {
			if (!map.containsKey(i)) {
				return 0;
			}
		}
		int[] max = new int[1];
		Set<Integer> used = new HashSet<Integer>();
		countArrangementUtil(map, 0, max, used);
		return max[0];
	}

	private void countArrangementUtil(Map<Integer, List<Integer>> map, int index, int[] max, Set<Integer> used) {
		if (index == map.size()) {
			max[0]++;
			return;
		}
		List<Integer> lst = map.get(index + 1);
		for (int i = 0; i < lst.size(); i++) {
			int num = lst.get(i);
			if (used.contains(num)) {
				continue;
			}
			used.add(num);
			countArrangementUtil(map, index + 1, max, used);
			used.remove(num);
		}
		return;
	}
}
