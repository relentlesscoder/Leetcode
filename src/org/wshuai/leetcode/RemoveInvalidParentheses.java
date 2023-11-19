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
		int n = s.length(), l = 0, r = 0;
		// find number of '(' and ')' to be removed
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(c == '('){
				l++;
			}else if(c == ')'){
				if(l == 0){
					r++;
				}else{
					l--;
				}
			}
		}
		// use dfs to find all valid results after removals
		dfs(s, 0, l, r, res);
		return res;
	}

	private void dfs(String s, int start, int open, int close, List<String> res){
		if(open == 0 && close == 0){
			if(isValid(s)){
				res.add(s);
			}
			return;
		}
		for(int i = start; i < s.length(); i++){
			// remove duplicate branches "((())"
			if(i != start && s.charAt(i) == s.charAt(i - 1)){
				continue;
			}
			char cur = s.charAt(i);
			if(cur == '(' || cur == ')'){
				String t = s.substring(0, i) + s.substring(i + 1);
				if(cur == '(' && open > 0){
					dfs(t, i, open - 1, close, res);
				}else if(cur == ')' && close > 0){
					dfs(t, i, open, close - 1, res);
				}
			}
		}
	}

	private boolean isValid(String s){
		int v = 0;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == '('){
				v++;
			}else if(c == ')'){
				if(--v < 0){
					return false;
				}
			}
		}
		return true;
	}
}
