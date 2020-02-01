package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/20/2017.
 * #0301 https://leetcode.com/problems/remove-invalid-parentheses/
 */
public class RemoveInvalidParentheses {
	// time O(2^(l+r)), space O((l+r)^2)
	// https://www.youtube.com/watch?v=2k_rS_u6EBk
	public List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<>();
		if(s == null || s.isEmpty()){
			res.add("");
			return res;
		}
		int l = 0, r = 0;
		for(char c : s.toCharArray()){
			if(c != '(' && c != ')'){
				continue;
			}
			l += (c == '(' ? 1 : 0);
			if(l == 0){
				r += (c == ')' ? 1 : 0);
			}else{
				l -= (c == ')' ? 1 : 0);
			}
		}
		dfs(s, 0, l, r, res);
		return res;
	}

	private void dfs(String s, int start, int open, int close, List<String> res){
		if(open == 0 && close == 0){
			if(valid(s)){
				res.add(s);
			}
			return;
		}
		for(int i = start; i < s.length(); i++){
			char cur = s.charAt(i);
			if(i != start && cur == s.charAt(i - 1)){
				continue;
			}
			if(cur == '(' || cur == ')'){
				String t = s.substring(0, i) + s.substring(i + 1);
				if(close > 0 && cur == ')'){
					dfs(t, i, open, close - 1, res);
				}else if(open > 0 && cur == '('){
					dfs(t, i, open - 1, close, res);
				}
			}
		}
	}

	private boolean valid(String s){
		int count = 0;
		for(char c : s.toCharArray()){
			if(c != '(' && c != ')'){
				continue;
			}
			count += c == '(' ? 1 : -1;
			if(count < 0){
				return false;
			}
		}
		return count == 0;
	}
}
