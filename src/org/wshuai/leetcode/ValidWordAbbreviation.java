package org.wshuai.leetcode;

/**
 * Created by Wei on 10/27/2016.
 * #0408 https://leetcode.com/problems/valid-word-abbreviation/
 */
public class ValidWordAbbreviation {

	// time O(n)
	public boolean validWordAbbreviation(String word, String abbr) {
		int m = word.length(), n = abbr.length(), i = 0, j = 0, k = j;
		if(m < n){
			return false;
		}
		for(; i < m && j < n; i++, j++, k = j){
			char c1 = abbr.charAt(j);
			// leading 0 is invalid
			if(c1 == '0'){
				return false;
			}
			if(Character.isAlphabetic(c1)){
				if(c1 != word.charAt(i)){
					return false;
				}
			}else{
				while(j + 1 < n && Character.isDigit(abbr.charAt(j + 1))){
					j++;
				}
				int count = Integer.parseInt(abbr.substring(k, j + 1));
				i += count - 1;
			}
		}
		return i == m && j == n;
	}
}
