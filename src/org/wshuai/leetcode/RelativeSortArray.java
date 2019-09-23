package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 8/9/19.
 * #1122 https://leetcode.com/problems/relative-sort-array/
 */
public class RelativeSortArray {
	public int[] relativeSortArray(int[] arr1, int[] arr2) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Set<Integer> set = new HashSet<Integer>();
		Queue<Integer> queue = new PriorityQueue<Integer>();
		for (int v : arr2) {
			set.add(v);
		}
		for (int v : arr1) {
			if (set.contains(v)) {
				map.put(v, map.getOrDefault(v, 0) + 1);
			} else {
				queue.offer(v);
			}
		}
		int j = 0;
		for (int i = 0; i < arr2.length; i++) {
			int count = map.get(arr2[i]);
			while (count > 0) {
				arr1[j++] = arr2[i];
				count--;
			}
			map.remove(arr2[i]);
		}
		while (!queue.isEmpty()) {
			arr1[j++] = queue.poll();
		}
		return arr1;
	}
}
