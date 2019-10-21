package org.wshuai.leetcode;

/**
 * Created by Wei on 10/16/2019.
 * #809 https://leetcode.com/problems/expressive-words/
 */
public class ExpressiveWords {
	public int expressiveWords(String S, String[] words) {
		int res = 0;
		for(String word: words){
			if(check(S, word)){
				res++;
			}
		}
		return res;
	}

	private boolean check(String s, String t){
		if(t.length() > s.length()){
			return false;
		}
		int i = 0;
		int j = 0;
		while(i < s.length() && j < t.length()){
			char next = s.charAt(i);
			int c1 = 0;
			int c2 = 0;
			while(i < s.length() && s.charAt(i) == next){
				c1++;
				i++;
			}
			while(j < t.length() && t.charAt(j) == next){
				c2++;
				j++;
			}
			if(!(c1 == c2 || (c1 > c2 && c1 >= 3))){
				return false;
			}
		}
		return i == s.length() && j == t.length();
	}
}
