package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/29/2016.
 * #0438 https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsInAString {

	// time O(n)
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<>();
		int[] map = new int[26], cur = new int[26];
		for(char c : p.toCharArray()){
			map[c - 'a']++;
		}
		int n = p.length();
		for(int i = 0, j = 0; j < s.length(); j++){
			cur[s.charAt(j) - 'a']++;
			if(j >= n){
				cur[s.charAt(i++) - 'a']--;
			}
			if(Arrays.equals(map, cur)){
				res.add(i);
			}
		}
		return res;
	}
}
