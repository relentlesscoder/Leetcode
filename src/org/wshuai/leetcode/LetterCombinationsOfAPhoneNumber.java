package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0017 https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber {
	private static final char[][] map = new char[][]{
		{'a', 'b', 'c'},
		{'d', 'e', 'f'},
		{'g', 'h', 'i'},
		{'j', 'k', 'l'},
		{'m', 'n', 'o'},
		{'p', 'q', 'r', 's'},
		{'t', 'u', 'v'},
		{'w', 'x', 'y', 'z'}
	};
	private List<String> res;

	// time O(4^n)
	public List<String> letterCombinations(String digits) {
		res = new ArrayList<>();
		if(digits == null || digits.isEmpty()){
			return res;
		}
		dfs(0, digits.toCharArray(), "");
		return res;
	}

	private void dfs(int i, char[] chars, String cur){
		if(i == chars.length){
			res.add(cur);
			return;
		}
		int k = chars[i] - '2';
		for(char c : map[k]){
			dfs(i + 1, chars, cur + c);
		}
	}
}
