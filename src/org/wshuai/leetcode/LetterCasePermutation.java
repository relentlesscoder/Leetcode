package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/19/2019.
 * #0784 https://leetcode.com/problems/letter-case-permutation/
 */
public class LetterCasePermutation {
	// time O(2^n)
	public List<String> letterCasePermutation(String S) {
		List<String> res = new ArrayList<>();
		dfs(0, S.length(), S.toCharArray(), res);
		return res;
	}

	private void dfs(int start, int n, char[] cur, List<String> res) {
		if (start == n) {
			res.add(new String(cur));
			return;
		}
		dfs(start + 1, n, cur, res);
		char c = cur[start];
		if (Character.isAlphabetic(c)) {
			if (cur[start] >= 'a' && cur[start] <= 'z') {
				cur[start] = (char) (c - 32);
				dfs(start + 1, n, cur, res);
			} else {
				cur[start] = (char) (c + 32);
				dfs(start + 1, n, cur, res);
			}
			cur[start] = c;
		}
	}
}
