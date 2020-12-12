package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2020.
 * #1684 https://leetcode.com/problems/count-the-number-of-consistent-strings/
 */
public class CountTheNumberOfConsistentStrings {

	// time O(n)
	public int countConsistentStrings(String allowed, String[] words) {
		int map = 0;
		int res = 0;
		for(char c : allowed.toCharArray()){
			map |= 1 << (c - 'a');
		}
		for(String word : words){
			int cur = map;
			for(char c : word.toCharArray()){
				cur |= 1 << (c - 'a');
			}
			if(cur == map){
				res++;
			}
		}
		return res;
	}
}
