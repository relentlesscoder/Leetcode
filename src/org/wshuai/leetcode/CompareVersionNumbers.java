package org.wshuai.leetcode;

/**
 * Created by Wei on 09/02/2016.
 * #0165 https://leetcode.com/problems/compare-version-numbers/
 */
public class CompareVersionNumbers {
	// time O(max(m, n))
	public int compareVersion(String version1, String version2) {
		int i = 0, j = 0, s = 0, t = 0, v1 = -1, v2 = -1,
			m = version1.length(), n = version2.length();
		while(i < m || s < n){
			while(i < m && version1.charAt(i) != '.'){
				i++;
			}
			while(s < n && version2.charAt(s) != '.'){
				s++;
			}
			v1 = i == j ? 0 : Integer.parseInt(version1.substring(j, i));
			v2 = s == t ? 0 : Integer.parseInt(version2.substring(t, s));
			if(v1 == v2){
				j = ++i;
				t = ++s;
				continue;
			}
			return v1 > v2 ? 1 : -1;
		}
		return 0;
	}
}
