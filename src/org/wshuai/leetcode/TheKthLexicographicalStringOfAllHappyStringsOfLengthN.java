package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 04/19/2020.
 * #1415 https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
 */
public class TheKthLexicographicalStringOfAllHappyStringsOfLengthN {
	// time O(3^n)
	public String getHappyString(int n, int k) {
		List<String> res = new ArrayList<>();
		dfs(n, k, "", res);
		return res.size() == k ? res.get(k - 1) : "";
	}

	private void dfs(int n, int k, String cur, List<String> res){
		if(res.size() == k){
			return;
		}
		int l = cur.length();
		if(l == n){
			res.add(cur);
			return;
		}
		for(char i = 'a'; i <= 'c'; i++){
			if(l > 0 && cur.charAt(l - 1) == i){
				continue;
			}
			dfs(n, k, cur + i, res);
		}
	}
}
