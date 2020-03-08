package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/19/2019.
 * #0446 https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
 */
public class ArithmeticSlicesIISubsequence {
	// time O(n^2), space O(n)
	public int numberOfArithmeticSlices(int[] A) {
		int res = 0, n = A.length;
		// for each number at i in the array, map[i] stores the number
		// of elements before with distance key to itself
		Map<Integer, Integer>[] map = new Map[n];
		for(int i = 0; i < n; i++){
			map[i] = new HashMap<>();
			for(int j = 0; j < i; j++){
				long diff = (long)A[i] - A[j];
				if(diff <= Integer.MIN_VALUE || diff >= Integer.MAX_VALUE){
					continue;
				}
				int val = (int)diff;
				int count1 = map[i].getOrDefault(val, 0),
						count2 = map[j].getOrDefault(val, 0);
				// since the sequence already has two element j and i. the number of
				// sequence end with i through j is the number of elements before j
				// with distance val to j
				res += count2;
				// update the number elements before i with distance val to i
				map[i].put(val, count1 + count2 + 1);
			}
		}
		return res;
	}
}
