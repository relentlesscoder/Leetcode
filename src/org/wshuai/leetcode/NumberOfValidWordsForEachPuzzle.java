package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 12/25/2019.
 * #1178 https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/
 */
public class NumberOfValidWordsForEachPuzzle {
	public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
		Map<Integer, Integer> wordMasks = new HashMap<>();
		for(int i = 0; i < words.length; i++){
			int mask = 0;
			for(char c : words[i].toCharArray()){
				mask = (mask | (1 << (c - 'a')));
			}
			wordMasks.put(mask, wordMasks.getOrDefault(mask, 0) + 1);
		}
		List<Integer> res = new ArrayList<>();
		for(int i = 0; i < puzzles.length; i++){
			String p = puzzles[i];
			int firstLetterMask = (1 << (p.charAt(0) - 'a'));
			int puzzleMask = 0;
			for(char c : p.toCharArray()){
				puzzleMask = (puzzleMask | (1 << (c - 'a')));
			}
			int cnt = 0;
			int subMask = puzzleMask;
			while(true){
				if((subMask & firstLetterMask) == firstLetterMask && wordMasks.containsKey(subMask)){
					cnt += wordMasks.get(subMask);
				}
				if(subMask == 0){
					break;
				}
				// key trick is this line
				// subMask decrease 1 each time to reach 0 to include all possible substring combinations
				// & puzzleMask part can make sure it is a subset of original bit mask
				subMask = (subMask - 1) & puzzleMask;
			}
			res.add(cnt);
		}
		return res;
	}
}
