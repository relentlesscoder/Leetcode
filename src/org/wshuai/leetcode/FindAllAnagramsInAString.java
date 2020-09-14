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
		if(s == null || s.isEmpty() || p == null || p.isEmpty()){
			return res;
		}
		int n = p.length();
		int[] target = new int[26], cur = new int[26];
		for(char c : p.toCharArray()){
			target[c - 'a']++;
		}
		for(int i = 0, j = 0; j < s.length(); j++){
			char c = s.charAt(j);
			int index = c - 'a';
			++cur[index];
			while(cur[index] > target[index]){
				cur[s.charAt(i++) - 'a']--;
			}
			if(j - i + 1 == n){
				res.add(i);
			}
		}
		return res;
	}
}
