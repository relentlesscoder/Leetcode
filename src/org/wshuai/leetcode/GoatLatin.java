package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2019.
 * #0824 https://leetcode.com/problems/goat-latin/
 */
public class GoatLatin {

	// time O(n), space O(n)
	public String toGoatLatin(String S) {
		int n = S.length();
		StringBuilder res = new StringBuilder(), postfix = new StringBuilder("a");
		for(int i = 0, j = 0; i < n; i++){
			StringBuilder word = new StringBuilder();
			char cur = S.charAt(i);
			boolean vowel = (cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o'
					|| cur == 'u' || cur == 'A' || cur == 'E' || cur == 'I' || cur == 'O' || cur == 'U');
			j = vowel ? i : i + 1;
			while(j < n && S.charAt(j) != ' '){
				word.append(S.charAt(j++));
			}
			res.append(word.toString()).append(vowel ? "" : "" + cur)
					.append("ma").append(postfix.toString()).append(" ");
			postfix.append("a");
			i = j;
		}
		return res.substring(0, res.length() - 1);
	}
}
