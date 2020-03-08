package org.wshuai.leetcode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 10/15/2019.
 * #0524 https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 */
public class LongestWordInDictionaryThroughDeleting {
	// time O(max(n*d, n*log(n)))
	public String findLongestWord(String s, List<String> d) {
		Collections.sort(d, (a, b) -> a.length() == b.length() ?
			a.compareTo(b) : b.length() - a.length());
		for(String word: d){
			if(s.equals(word) || canFormByDeleting(s, word)){
				return word;
			}
		}
		return "";
	}

	private boolean canFormByDeleting(String s, String t){
		int i = 0, j = 0;
		while(i < s.length() && j < t.length()){
			if(s.charAt(i) == t.charAt(j)){
				i++;
				j++;
			}else{
				i++;
			}
		}
		return j == t.length();
	}
}
