package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Wei on 08/07/2019.
 * #0760 https://leetcode.com/problems/find-anagram-mappings/
 */
public class FindAnagramMappings {
	// time O(n), space O(n)
	public int[] anagramMappings(int[] A, int[] B) {
		int n = A.length;
		int[] res = new int[n];
		Map<Integer, Stack<Integer>> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			map.putIfAbsent(B[i], new Stack<>());
			map.get(B[i]).push(i);
		}
		for(int i = 0; i < n; i++){
			res[i] = map.get(A[i]).pop();
		}
		return res;
	}
}
