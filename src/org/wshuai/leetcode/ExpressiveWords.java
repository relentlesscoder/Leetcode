package org.wshuai.leetcode;

/**
 * Created by Wei on 10/16/2019.
 * #0809 https://leetcode.com/problems/expressive-words/
 */
public class ExpressiveWords {

	// time O(m*d)
	public int expressiveWords(String S, String[] words) {
		int res = 0;
		for(String cur : words){
			if(canForm(S, cur)){
				res++;
			}
		}
		return res;
	}

	private boolean canForm(String S, String T){
		int i = 0, j = 0, m = S.length(), n = T.length();
		while(i < m && j < n){
			char cur = S.charAt(i);
			int c1 = 0, c2 = 0;
			while(i < m && S.charAt(i) == cur){
				c1++;
				i++;
			}
			while(j < n && T.charAt(j) == cur){
				c2++;
				j++;
			}
			if(!(c1 == c2 || (c1 > c2 && c1 >= 3))){
				return false;
			}
		}
		return i == m && j == n;
	}
}
