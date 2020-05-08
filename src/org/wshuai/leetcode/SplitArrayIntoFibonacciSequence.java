package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/05/2019.
 * #0842 https://leetcode.com/problems/split-array-into-fibonacci-sequence/
 */
public class SplitArrayIntoFibonacciSequence {

	public List<Integer> splitIntoFibonacci(String S) {
		List<Integer> res = new ArrayList<>();
		dfs(0, S.length(), S.toCharArray(), res);
		return res;
	}

	private boolean dfs(int start, int n, char[] digits, List<Integer> res){
		if(start == n){
			return res.size() >= 3;
		}
		long cur = 0;
		int size = res.size();
		if(digits[start] == '0'){
			if(size < 2 || res.get(size - 1) + res.get(size - 2) == 0){
				res.add(0);
				if(dfs(start + 1, n, digits, res)){
					return true;
				}
				// only remove when all attempt failed
				res.remove(res.size() - 1);
				return false;
			}else{
				return false;
			}
		}
		for(int i = start; i < n; i++){
			cur = cur * 10 + (digits[i] - '0');
			if(cur > Integer.MAX_VALUE){
				break;
			}
			int next = (int)cur;
			if(size < 2 || res.get(size - 1) + res.get(size - 2) == next){
				res.add(next);
				if(dfs(i + 1, n, digits, res)){
					return true;
				}
				// only remove when all attempt failed
				res.remove(res.size() - 1);
			}
		}
		return false;
	}
}
