package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0017 https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber {

	private static final char[][] MAPPING = new char[][]{
			{'a', 'b', 'c'},
			{'d', 'e', 'f'},
			{'g', 'h', 'i'},
			{'j', 'k', 'l'},
			{'m', 'n', 'o'},
			{'p', 'q', 'r', 's'},
			{'t', 'u', 'v'},
			{'w', 'x', 'y', 'z'}
	};

	// time O(4^n)
	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		if(digits == null || digits.isEmpty()){
			return res;
		}
		dfs(0, digits.toCharArray(), new char[digits.length()], res);
		return res;
	}

	private void dfs(int i, char[] chars, char[] cur, List<String> res){
		if(i == chars.length){
			res.add(String.valueOf(cur));
			return;
		}
		for(char c : MAPPING[chars[i] - '2']){
			cur[i] = c;
			dfs(i + 1, chars, cur, res);
		}
	}
}
