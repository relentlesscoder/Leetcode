package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 07/06/2017.
 * #0282 https://leetcode.com/problems/expression-add-operators/
 */
public class ExpressionAddOperators {

	// time O(3^n)
	// See https://discuss.leetcode.com/topic/24523/java-standard-backtrace-ac-solutoin-short-and-clear
	public List<String> addOperators(String num, int target) {
		List<String> res = new ArrayList<>();
		if(num == null || num.length() == 0){
			return res;
		}
		dfs(0, 0, 0, "", num, target, res);
		return res;
	}

	private void dfs(int start, long cur, long mult, String expr, String num, int target, List<String> res){
		if(start == num.length()){
			if(cur == target){
				res.add(expr);
			}
			return;
		}
		long val = 0L;
		for(int i = start; i < num.length(); i++){
			if(i != start && num.charAt(start) == '0'){ // leading 0 is invalid
				break;
			}
			val = val * 10 + (num.charAt(i) - '0');
			if(start == 0){
				dfs(i + 1, cur + val, val, expr + val, num, target, res);
			}else{
				dfs(i + 1, cur + val, val, expr + "+" + val, num, target, res);
				dfs(i + 1, cur - val, -val, expr + "-" + val, num, target, res);
				dfs(i + 1, cur - mult + mult * val, mult * val, expr + "*" + val, num, target, res); // special case for multiply
			}
		}
	}
}
