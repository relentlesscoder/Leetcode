package org.wshuai.leetcode;

/**
 * Created by Wei on 12/08/2020.
 * #1668 https://leetcode.com/problems/maximum-repeating-substring/
 */
public class MaximumRepeatingSubstring {

	// time O(n^2)
	public int maxRepeating(String sequence, String word) {
		int res = 0;
		String repeat = word;
		while(sequence.contains(repeat)){
			res++;
			repeat += word;
		}
		return res;
	}
}
