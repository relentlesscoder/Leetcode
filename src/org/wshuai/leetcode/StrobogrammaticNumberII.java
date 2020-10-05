package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/24/2016.
 * #0247 https://leetcode.com/problems/strobogrammatic-number-ii/
 */
public class StrobogrammaticNumberII {

	private static final char[][] map = new char[][]{
			{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}
	};
	private static final char[] mid = new char[]{'0', '1', '8'};

	// time 5^(n/2)
	public List<String> findStrobogrammatic(int n) {
		List<String> res = new ArrayList<>();
		if(n == 0){
			return res;
		}
		if(n % 2 == 0){
			dfs(n, 0, n - 1, res, new char[n]);
		}else{
			for(int i = 0; i < 3; i++){
				char[] arr = new char[n];
				arr[n >> 1] = mid[i];
				dfs(n - 1, 0, n - 1, res, arr);
			}
		}
		return res;
	}

	private void dfs(int n, int i, int j, List<String> res, char[] arr){
		if(n == 0){
			if(arr[0] == '0' && arr.length > 1){
				return;
			}
			res.add(new String(arr));
			return;
		}
		for(int k = 0; k < 5; k++){
			arr[i] = map[k][0];
			arr[j] = map[k][1];
			dfs(n - 2, i + 1, j - 1, res, arr);
		}
	}
}
