package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 07/02/2017.
 * #0248 https://leetcode.com/problems/strobogrammatic-number-iii/
 */
public class StrobogrammaticNumberIII {

	private static final char[][] MAPPING = new char[][]{
			{'0','0'},{'1','1'},{'6','9'},{'8','8'},{'9','6'}
	};
	private static final char[] MID = new char[]{'0','1','8'};

	// time O(sum(5^(d/2))
	public int strobogrammaticInRange(String low, String high) {
		int res = 0;
		List<String> list = new ArrayList<String>();
		for(int n = low.length(); n <= high.length(); n++){
			list.addAll(findStrobogrammatic(n));
		}
		for(String num : list){
			if((num.length() == low.length() && num.compareTo(low) < 0 )
					|| (num.length() == high.length() && num.compareTo(high) > 0)){
				continue;
			}
			res++;
		}
		return res;
	}

	public List<String> findStrobogrammatic(int n) {
		List<String> res = new ArrayList<>();
		if(n <= 0){
			return res;
		}
		int m = (n - 1)/2;
		if(n % 2 == 1){
			for(char c : MID){
				char[] arr = new char[n];
				arr[m] = c;
				dfs(n - 1, m - 1, m + 1, MAPPING, arr, res);
			}
		}else{
			dfs(n, m, m + 1, MAPPING, new char[n], res);
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
		}
	}
}
