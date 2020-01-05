package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/05/2020.
 * #0022 https://leetcode.com/problems/generate-parentheses/
 */
public class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		dfs(res, "", 0, 0, n);
		return res;
	}

	private void dfs(List<String> res, String cur, int open, int close, int n){
		if(cur.length() == 2 * n){
			res.add(cur);
			return;
		}
		if(open < n){
			dfs(res, cur + '(', open + 1, close, n);
		}
		if(close < open){
			dfs(res, cur + ')', open, close + 1, n);
		}
	}
}
