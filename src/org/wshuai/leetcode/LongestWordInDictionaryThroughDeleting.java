package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 10/15/2019.
 * #524 https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 */
public class LongestWordInDictionaryThroughDeleting {
	public String findLongestWord(String s, List<String> d) {
		int max = 0;
		String res = "";
		for(String word: d){
			if(word.length() > s.length()){
				continue;
			}
			if(s.equals(word) || canFormByDeleting(s, word)){
				if(word.length() > max || (word.length() == max && word.compareTo(res) < 0)){
					max = word.length();
					res = word;
				}
			}
		}
		return res;
	}

	private boolean canFormByDeleting(String s, String t){
		int i = 0;
		int j = 0;
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
