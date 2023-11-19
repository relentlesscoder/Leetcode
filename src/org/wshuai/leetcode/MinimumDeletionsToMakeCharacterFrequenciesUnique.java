package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 12/12/2020.
 * #1647 https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 */
public class MinimumDeletionsToMakeCharacterFrequenciesUnique {

	public int minDeletions(String s) {
		int res = 0;
		int[] count = new int[26];
		Set<Integer> used = new HashSet<>();
		for(char c : s.toCharArray()){
			count[c - 'a']++;
		}
		for(int c : count){
			while(c > 0 && !used.add(c)){
				res++;
				c--;
			}
		}
		return res;
	}
}
