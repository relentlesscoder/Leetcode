package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/19/16.
 * #241 https://leetcode.com/problems/different-ways-to-add-parentheses/
 */
public class DifferentWaysToAddParentheses {
	//O(n^2), Divide & Conquer
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> lst = new ArrayList<Integer>();
		int len = input.length();
		for (int i = 0; i < len; i++) {
			char val = input.charAt(i);
			if (val == '+' || val == '-' || val == '*') {
				List<Integer> left = diffWaysToCompute(input.substring(0, i));
				List<Integer> right = diffWaysToCompute(input.substring(i + 1));
				int lLen = left.size();
				int rLen = right.size();
				for (int j = 0; j < lLen; j++) {
					for (int k = 0; k < rLen; k++) {
						int res;
						if (val == '+') {
							res = left.get(j) + right.get(k);
						} else if (val == '-') {
							res = left.get(j) - right.get(k);
						} else {
							res = left.get(j) * right.get(k);
						}
						lst.add(res);
					}
				}
			}
		}
		//Input does not contain operators
		if (lst.size() == 0) {
			lst.add(Integer.parseInt(input));
		}
		return lst;
	}
}
