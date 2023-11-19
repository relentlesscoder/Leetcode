package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2016.
 * #0161 https://leetcode.com/problems/one-edit-distance/
 */
public class OneEditDistance {

	// time O(n)
	public boolean isOneEditDistance(String s, String t) {
		int m = s.length(), n = t.length(), diff = Math.abs(m - n);
		if(diff > 1){
			return false;
		}
		for(int i = 0; i < Math.min(m, n); i++){
			if(s.charAt(i) == t.charAt(i)){
				continue;
			}
			if(m > n){
				return equal(s, i + 1, t, i);
			}else if(m < n){
				return equal(s, i, t, i + 1);
			}else{
				return equal(s, i + 1, t, i + 1);
			}
		}
		return diff == 1;
	}

	private boolean equal(String s, int i, String t, int j){
		for( ; i < s.length() && s.charAt(i) == t.charAt(j); i++, j++);
		return i == s.length();
	}
}
