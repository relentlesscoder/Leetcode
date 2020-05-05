package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2019.
 * #0824 https://leetcode.com/problems/goat-latin/
 */
public class GoatLatin {
	// time O(n), space O(n)
	public String toGoatLatin(String S) {
		StringBuilder res = new StringBuilder();
		String[] strs = S.split("\\s");
		for(int i = 0; i < strs.length; i++){
			char c = strs[i].charAt(0);
			if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
					|| c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
				res.append(strs[i]);
			}else{
				res.append(strs[i].substring(1) + c);
			}
			res.append("ma");
			for(int j = 0; j <= i; j++){
				res.append("a");
			}
			res.append(" ");
		}
		return res.substring(0, res.length() - 1);
	}
}
