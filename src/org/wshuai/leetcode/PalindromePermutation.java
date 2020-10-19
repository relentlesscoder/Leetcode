package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/19/2016.
 * #0266 https://leetcode.com/problems/palindrome-permutation/
 */
public class PalindromePermutation {

	// time O(n), space O(n)
	public boolean canPermutePalindrome(String s) {
		Set<Character> set = new HashSet<>();
		for(char c : s.toCharArray()){
			if(set.contains(c)){
				set.remove(c);
			}else{
				set.add(c);
			}
		}
		return set.isEmpty() || set.size() == 1;
	}
}
