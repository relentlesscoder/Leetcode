package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2016.
 * #0392 https://leetcode.com/problems/is-subsequence/
 */
public class IsSubsequence {
	// time O(n)
	public boolean isSubsequence(String s, String t) {
		int i = 0, j = 0, m = s.length(), n = t.length();
		while(i < m && j < n){
			if(s.charAt(i) == t.charAt(j)){
				i++;
			}
			j++;
		}
		return i == m;
	}
}
