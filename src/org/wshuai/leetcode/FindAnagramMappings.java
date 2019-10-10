package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Wei on 8/7/19.
 * #760 https://leetcode.com/problems/find-anagram-mappings/
 */
public class FindAnagramMappings {
	public int[] anagramMappings(int[] A, int[] B) {
		int len = A.length;
		Map<Integer, Stack<Integer>> map = new HashMap<Integer, Stack<Integer>>();
		for (int i = 0; i < len; i++) {
			if (map.containsKey(B[i])) {
				map.get(B[i]).push(i);
			} else {
				Stack<Integer> lst = new Stack<Integer>();
				lst.push(i);
				map.put(B[i], lst);
			}
		}
		int[] res = new int[len];
		for (int i = 0; i < len; i++) {
			res[i] = map.get(A[i]).pop();
		}
		return res;
	}
}
