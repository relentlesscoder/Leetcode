package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/19/19.
 * #784 https://leetcode.com/problems/letter-case-permutation/
 */
public class LetterCasePermutation {
	public List<String> letterCasePermutation(String S) {
		List<String> lst = new ArrayList<>();
		letterCasePermutationUtil(S, "", 0, lst);
		return lst;
	}

	private void letterCasePermutationUtil(String S, String curr, int idx, List<String> lst) {
		if (idx >= S.length()) {
			lst.add(curr);
			return;
		}
		char c = S.charAt(idx);
		if (Character.isDigit(c)) {
			letterCasePermutationUtil(S, curr + c, idx + 1, lst);
		} else {
			letterCasePermutationUtil(S, curr + Character.toLowerCase(c), idx + 1, lst);
			letterCasePermutationUtil(S, curr + Character.toUpperCase(c), idx + 1, lst);
		}
	}
}
