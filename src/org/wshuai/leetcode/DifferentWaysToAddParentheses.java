package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/19/2016.
 * #0241 https://leetcode.com/problems/different-ways-to-add-parentheses/
 */
public class DifferentWaysToAddParentheses {

	//time O(n^2)
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> res = new ArrayList<>();
		if(input == null || input.length() == 0){
			return res;
		}
		for(int i = 0; i < input.length(); i++){
			char c = input.charAt(i);
			if(c == '+' || c == '-' || c == '*'){
				List<Integer> left = diffWaysToCompute(input.substring(0, i));
				List<Integer> right = diffWaysToCompute(input.substring(i + 1));
				for(int j = 0; j < left.size(); j++){
					for(int k = 0; k < right.size(); k++){
						int v1 = left.get(j), v2 = right.get(k);
						if(c == '+'){
							res.add(v1 + v2);
						}else if(c == '-'){
							res.add(v1 - v2);
						}else{
							res.add(v1 * v2);
						}
					}
				}
			}
		}
		// base case: no operators found
		if(res.size() == 0){
			res.add(Integer.parseInt(input));
		}
		return res;
	}
}
