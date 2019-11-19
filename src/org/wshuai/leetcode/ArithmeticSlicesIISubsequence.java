package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/19/19.
 * #446 https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
 */
public class ArithmeticSlicesIISubsequence {

	public int numberOfArithmeticSlices(int[] A) {
		int res = 0;
		int N = A.length;
		Map<Integer, Integer>[] map = new Map[N];
		for(int i = 0; i < N; i++){
			map[i] = new HashMap<>();
			for(int j = 0; j < i; j++){
				long diff = (long)A[i] - A[j];
				if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE){
					continue;
				}
				int d = (int)diff;
				int c1 = map[i].getOrDefault(d, 0);
				int c2 = map[j].getOrDefault(d, 0);
				res += c2;
				// 1 is the [j, i] sequence for future calculation
				map[i].put(d, c1 + c2 + 1);
			}
		}
		return res;
	}

	Map<Integer, Integer>[] map;

	public int numberOfArithmeticSlicesMemorization(int[] A) {
		int res = 0;
		int N = A.length;
		map = new Map[N];
		for(int i = 0; i < N; i++){
			map[i] = new HashMap<>();
		}
		for(int i = 0; i < N; i++){
			for(int j = i + 1; j < N; j++){
				long diff = (long)A[j] - A[i];
				if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE){
					continue;
				}
				res += dfs(j + 1, A[j], (int)diff, A);
			}
		}
		return res;
	}

	private int dfs(int start, int prev, int diff, int[] A){
		while(start < A.length && (long)A[start] - prev != diff){
			start++;
		}
		if(start == A.length){
			return 0;
		}
		if(map[start].containsKey(diff)){
			return map[start].get(diff);
		}
		int res = 1;
		res += dfs(start + 1, A[start], diff, A);
		res += dfs(start + 1, prev, diff, A);
		map[start].put(diff, res);
		return res;
	}
}
