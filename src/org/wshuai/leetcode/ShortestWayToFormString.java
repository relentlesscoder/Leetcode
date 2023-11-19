package org.wshuai.leetcode;

/**
 * Created by Wei on 09/30/2019.
 * #1055 https://leetcode.com/problems/shortest-way-to-form-string/
 */
public class ShortestWayToFormString {

	// time O(m + n)
	public int shortestWay(String source, String target) {
		int res = 0, m = source.length(), n = target.length();
		boolean[] map = new boolean[26];
		for(char c : source.toCharArray()){
			map[c - 'a'] = true;
		}
		for(int i = 0, j = 0; j < n; i++){
			if(i % m == 0){
				res++;
			}
			char c1 = source.charAt(i % m), c2 = target.charAt(j);
			if(!map[c2 - 'a']){
				return -1;
			}
			if(c1 == c2){
				j++;
			}
		}
		return res;
	}
}
