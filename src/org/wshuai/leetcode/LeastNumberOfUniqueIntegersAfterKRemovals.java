package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 06/14/2020.
 * #5454 https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {

	// time O(n*log(n)), space O(n)
	public int findLeastNumOfUniqueInts(int[] arr, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		for(int a : arr){
			count.put(a, count.getOrDefault(a, 0) + 1);
		}
		int n = count.size(), res = n, i = 0;
		int[][] temp = new int[n][2];
		for(Map.Entry<Integer, Integer> entry : count.entrySet()){
			temp[i++] = new int[]{entry.getKey(), entry.getValue()};
		}
		Arrays.sort(temp, (a, b) -> a[1] - b[1]);
		for(int j = 0; j < n && k > 0; j++){
			int[] cur = temp[j];
			res -= cur[1] <= k ? 1 : 0;
			k -= Math.min(cur[1], k);
		}
		return res;
	}
}
