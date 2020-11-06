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
		dfs(0, 0, 0, "", num.toCharArray(), target, res);
		return res;
	}

	private void dfs(int start, long sum, long prod, String cur, char[] num, int target, List<String> res){
		if(start == num.length){
			if(sum == target){
				res.add(cur);
			}
			return;
		}
		long val = 0;
		for(int i = start; i < num.length; i++){
			if(i != start && num[start] == '0'){
				break;
			}
			val = val * 10 + (num[i] - '0');
			if(start == 0){
				dfs(i + 1, sum + val, val, "" + val, num, target, res);
			}else{
				dfs(i + 1, sum + val, val, cur + "+" + val, num, target, res);
				dfs(i + 1, sum - val, -val, cur + "-" + val, num, target, res);
				dfs(i + 1, sum - prod + prod * val, prod * val, cur + "*" + val, num, target, res);
			}
		}
	}
}
