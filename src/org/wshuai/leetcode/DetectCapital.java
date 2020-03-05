package org.wshuai.leetcode;

/**
 * Created by Wei on 02/25/2017.
 * #0520 https://leetcode.com/problems/detect-capital/
 */
public class DetectCapital {
	// time O(n)
	public boolean detectCapitalUse(String word) {
		int count = 0, index = -1;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(c >= 'A' && c <= 'Z'){
				count++;
				index = i;
			}
		}
		return count == 0
				|| (count == 1 && index == 0)
				|| count == word.length();
	}
}
