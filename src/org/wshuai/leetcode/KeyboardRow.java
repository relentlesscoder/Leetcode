package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 02/25/2017.
 * #0500 https://leetcode.com/problems/keyboard-row/
 */
public class KeyboardRow {
	public String[] findWords(String[] words) {
		int[] rowNumber = new int[26];
		String[] strs = new String[]{"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
		for(int i = 0; i < 3; i++){
			for(char c : strs[i].toCharArray()){
				rowNumber[c - 'A'] = i;
			}
		}
		List<String> res = new ArrayList<>();
		for(String word : words){
			boolean valid = true;
			String upper = word.toUpperCase();
			int curRow = rowNumber[upper.charAt(0) - 'A'];
			for(int i = 1; i < upper.length(); i++){
				if(rowNumber[upper.charAt(i) - 'A'] != curRow){
					valid = false;
					break;
				}
			}
			if(valid){
				res.add(word);
			}
		}
		return res.toArray(new String[res.size()]);
	}
}
