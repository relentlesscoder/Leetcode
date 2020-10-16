package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/05/2020.
 * #0022 https://leetcode.com/problems/generate-parentheses/
 */
public class GenerateParentheses {

	// time O(2^N), space O(N), N = 2*n
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		dfs(0, 0, n, new StringBuilder(), res);
		return res;
	}

	private void dfs(int open, int close, int n, StringBuilder cur, List<String> res){
		if(close == n){
			if(open == close){
				res.add(cur.toString());
			}
			return;
		}
		if(open < n){
			cur.append('(');
			dfs(open + 1, close, n, cur, res);
			cur.deleteCharAt(cur.length() - 1);
		}
		if(close < open){
			cur.append(')');
			dfs(open, close + 1, n, cur, res);
			cur.deleteCharAt(cur.length() - 1);
		}
	}
}
