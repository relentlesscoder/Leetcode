package org.wshuai.leetcode;

/**
 * Created by Wei on 08/10/2019.
 * #0844 https://leetcode.com/problems/backspace-string-compare/
 */
public class BackspaceStringCompare {

	// time O(n), space O(1)
	public boolean backspaceCompare(String S, String T) {
		int m = S.length(), n = T.length();
		for(int i = m - 1, j = n - 1, b1 = 0, b2 = 0; ;){
			while(i >= 0 && (S.charAt(i) == '#' || b1 > 0)){
				b1 += S.charAt(i--) == '#' ? 1 : -1;
			}
			while(j >= 0 && (T.charAt(j) == '#' || b2 > 0)){
				b2 += T.charAt(j--) == '#' ? 1 : -1;
			}
			if(i < 0 && j < 0){
				break;
			}
			if(i >= 0 && j >= 0 && S.charAt(i--) == T.charAt(j--)){
			}else{
				return false;
			}
		}
		return true;
	}
}
