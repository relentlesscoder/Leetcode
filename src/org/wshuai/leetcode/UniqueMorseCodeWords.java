package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 08/07/2019.
 * #0804 https://leetcode.com/problems/unique-morse-code-words/
 */
public class UniqueMorseCodeWords {
	// time O(n)
	public int uniqueMorseRepresentations(String[] words) {
		String[] map = new String[]{".-","-...","-.-.","-..",".","..-.",
				"--.","....","..",".---","-.-",".-..","--","-.","---",
				".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
		Set<String> res = new HashSet<>();
		for(String word : words){
			res.add(getMorseCode(word, map));
		}
		return res.size();
	}

	private String getMorseCode(String word, String[] map){
		StringBuilder res = new StringBuilder();
		for(char c : word.toCharArray()){
			res.append(map[c - 'a']);
		}
		return res.toString();
	}
}
