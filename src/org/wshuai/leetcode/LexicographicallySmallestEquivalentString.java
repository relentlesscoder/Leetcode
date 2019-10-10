package org.wshuai.leetcode;

/**
 * Created by Wei on 9/29/19.
 * #1061 https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
 */
public class LexicographicallySmallestEquivalentString {

	// union find, similar as #1101
	public String smallestEquivalentString(String A, String B, String S) {
		char[] map = new char[26];
		for(int i = 0; i < 26; i++){
			map[i] = (char)(i + 'a');
		}
		for(int i = 0; i < A.length(); i++){
			char a = A.charAt(i);
			char b = B.charAt(i);
			char ra = findRoot(a, map);
			char rb = findRoot(b, map);
			char min = ra <= rb ? ra : rb;
			map[ra - 'a'] = min;
			map[rb - 'a'] = min;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < S.length(); i++){
			char s = S.charAt(i);
			sb.append("" + findRoot(s, map));
		}
		return sb.toString();
	}

	private char findRoot(char c, char[] map){
		while(c != map[c - 'a']){
			c = map[c - 'a'];
		}
		return c;
	}
}
