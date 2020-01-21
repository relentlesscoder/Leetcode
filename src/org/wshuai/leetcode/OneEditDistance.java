package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2016.
 * #0161 https://leetcode.com/problems/one-edit-distance/
 */
public class OneEditDistance {
	// time O(n)
	public boolean isOneEditDistance(String s, String t) {
		if(s == null || t == null || s.equals(t)
			|| Math.abs(s.length() - t.length()) > 1){
			return false;
		}
		int m = s.length(), n = t.length(), i = 0, j = 0;
		boolean seen = false;
		while(i < m || j < n){
			if(i < m && j < n && s.charAt(i) == t.charAt(j)){
				i++;
				j++;
				continue;
			}
			if(seen){
				return false;
			}
			seen = true;
			i = m > n || m == n ? i + 1 : i;
			j = n > m || m == n ? j + 1 : j;
		}
		return true;
	}
}
