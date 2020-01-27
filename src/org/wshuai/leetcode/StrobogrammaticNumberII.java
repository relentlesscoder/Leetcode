package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/24/2016.
 * #0247 https://leetcode.com/problems/strobogrammatic-number-ii/
 */
public class StrobogrammaticNumberII {
	// time O(5^(n/2))
	public List<String> findStrobogrammatic(int n) {
		List<String> res = new ArrayList<>();
		if(n <= 0){
			return res;
		}
		char[][] map = new char[][]{
				{'0','0'},{'1','1'},{'6','9'},{'8','8'},{'9','6'}
		};
		char[] mid = new char[]{'0','1','8'};
		int m = (n - 1)/2;
		if(n % 2 == 1){
			for(char c : mid){
				char[] arr = new char[n];
				arr[m] = c;
				dfs(n - 1, m - 1, m + 1, map, arr, res);
			}
		}else{
			dfs(n, m, m + 1, map, new char[n], res);
		}
		return res;
	}

	private void dfs(int n, int i, int j, char[][] map, char[] cur, List<String> res){
		if(n == 0){
			if(cur.length > 1 && cur[0] == '0'){
				return;
			}
			res.add(new String(cur));
			return;
		}
		for(int k = 0; k < 5; k++){
			char c1 = cur[i], c2 = cur[j];
			cur[i] = map[k][0];
			cur[j] = map[k][1];
			dfs(n - 2, i - 1, j + 1, map, cur, res);
			cur[i] = c1;
			cur[j] = c2;
		}
	}
}
