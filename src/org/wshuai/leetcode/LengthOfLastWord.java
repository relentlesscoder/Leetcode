package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0058 https://leetcode.com/problems/length-of-last-word/
 */
public class LengthOfLastWord {
	// O(n), n is the length of last word plus trailing spaces (if any)
	public int lengthOfLastWord(String s) {
		int res = 0, n = s.length();
		boolean isWord = false;
		for(int i = n - 1; i >= 0; i--){
			char c = s.charAt(i);
			if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
				res++;
				// hits the right end of the last word
				if(!isWord){
					isWord = true;
				}
			// hits the space before the last word, job done
			}else if(isWord){
				break;
			}
		}
		return res;
	}
}
