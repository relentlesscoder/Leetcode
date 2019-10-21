package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/19/2019.
 * #967 https://leetcode.com/problems/numbers-with-same-consecutive-differences/
 */
public class NumbersWithSameConsecutiveDifferences {
	Set<Integer> set = new HashSet<>();

	public int[] numsSameConsecDiff(int N, int K) {
		set = new HashSet<>();
		for(int i = 0; i <= 9; i++){
			if(N > 1 && i == 0){
				continue;
			}
			dfs(N, K, i, i, 1);
		}
		int[] res = new int[set.size()];
		int j = 0;
		for(int s: set){
			res[j++] = s;
		}
		return res;
	}

	private void dfs(int N, int K, int last, int curr, int len){
		if(len == N){
			set.add(curr);
			return;
		}
		if(last - K >= 0){
			dfs(N, K, last - K, curr * 10 + last - K, len + 1);
		}
		if(last + K <= 9){
			dfs(N, K, last + K, curr * 10 + last + K, len + 1);
		}
	}
}
