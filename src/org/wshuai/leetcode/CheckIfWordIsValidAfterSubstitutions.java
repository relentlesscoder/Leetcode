package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/19.
 * #1003 https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/
 */
public class CheckIfWordIsValidAfterSubstitutions {
	public boolean isValid(String S) {
		while(S.contains("abc")){
			S = S.replace("abc", "");
		}
		return S.equals("");
	}
}
