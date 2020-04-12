package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Wei on 04/12/2020.
 * #1409 https://leetcode.com/problems/queries-on-a-permutation-with-key/
 */
public class QueriesOnAPermutationWithKey {
	// time O(n*log(m)), space O(m)
	public int[] processQueries(int[] queries, int m) {
		int n = queries.length, min = 0;
		int[] res = new int[n];
		TreeSet<Integer> set = new TreeSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 1; i <= m; i++){
			set.add(i);
		}
		for(int i = 0; i < n; i++){
			int num = map.getOrDefault(queries[i], queries[i]);
			res[i] = set.headSet(num).size();
			set.remove(num);
			set.add(min);
			map.put(queries[i], min);
			min--;
		}
		return res;
	}
}
