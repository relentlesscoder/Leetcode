package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2016.
 * #0161 https://leetcode.com/problems/one-edit-distance/
 */
public class OneEditDistance {

	// time O(n), space O(n)
	public boolean isOneEditDistance(String s, String t) {
		int m = s.length(), n = t.length();
		for(int i = 0; i < Math.min(m, n); i++){
			if(s.charAt(i) != t.charAt(i)){
				if(m == n){ // replace one char
					return s.substring(i + 1).equals(t.substring(i + 1));
				}else if(m < n){ // delete from t
					return s.substring(i).equals(t.substring(i + 1));
				}else{ // delete from s
					return s.substring(i + 1).equals(t.substring(i));
				}
			}
		}
		// delete the last char from the longer string
		return Math.abs(m - n) == 1;
	}
}
