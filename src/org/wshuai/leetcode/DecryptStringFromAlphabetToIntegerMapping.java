package org.wshuai.leetcode;

/**
 * Created by Wei on 01/05/2020.
 * #1309 https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
 */
public class DecryptStringFromAlphabetToIntegerMapping {
	public String freqAlphabets(String s) {
		int n = s.length();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i < n){
			if(i < n - 2 && s.charAt(i + 2) == '#'){
				int val = (s.charAt(i) - '0') * 10
					+ (s.charAt(i + 1) - '0') - 1;
				sb.append((char)('a' + val));
				i += 3;
			}else{
				sb.append((char)('a' + s.charAt(i) - '1'));
				i++;
			}
		}
		return sb.toString();
	}
}
