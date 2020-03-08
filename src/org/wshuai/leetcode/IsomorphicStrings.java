package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/12/2016.
 * #0205 https://leetcode.com/problems/isomorphic-strings/
 */
public class IsomorphicStrings {
	// time O(n), space O(1)
	public boolean isIsomorphic(String s, String t) {
		int n = s.length();
		int[] map1 = new int[256], map2 = new int[256];
		Arrays.fill(map1, -1);
		Arrays.fill(map2, -1);
		for(int i = 0; i < n; i++){
			char c1 = s.charAt(i), c2 = t.charAt(i);
			int v1 = (int)c1, v2 = (int)c2;
			if(map1[c1] == -1 && map2[c2] == -1){
				map1[c1] = v2;
				map2[c2] = v1;
				continue;
			}
			if(map1[c1] != v2 || map2[c2] != v1){
				return false;
			}
		}
		return true;
	}
}
