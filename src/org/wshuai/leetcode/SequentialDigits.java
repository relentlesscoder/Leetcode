package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/15/2019.
 * #1291 https://leetcode.com/problems/sequential-digits/
 */
public class SequentialDigits {
	public List<Integer> sequentialDigits(int low, int high) {
		List<Integer> res = new ArrayList<>();
		int len1 = Integer.toString(low).length();
		int len2 = Integer.toString(high).length();
		for(int i = len1; i <= len2; i++){
			res.addAll(dfs(i, low, high));
		}
		return res;
	}

	private List<Integer> dfs(int k, int low, int high){
		List<Integer> res = new ArrayList<>();
		for(int i = 1; i <= 9 - k + 1; i++){
			long x = 0;
			for(int j = 0; j < k; j++){
				x = x * 10 + (i + j);
			}
			if(x >= low && x <= high){
				res.add((int)x);
			}
		}
		return res;
	}
}
