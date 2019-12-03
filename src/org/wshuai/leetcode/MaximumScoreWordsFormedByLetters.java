package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/3/19.
 * #1255 https://leetcode.com/problems/maximum-score-words-formed-by-letters/
 */
public class MaximumScoreWordsFormedByLetters {
	Map<String, Integer> map;

	public int maxScoreWords(String[] words, char[] letters, int[] score) {
		map = new HashMap<>();
		int[] count = new int[26];
		for(char c : letters){
			count[c - 'a']++;
		}
		return dfs(words, 0, 0, count, score);
	}

	private int dfs(String[] words, int bitMask, int cur, int[] count, int[] score){
		if(cur == words.length){
			return 0;
		}
		String key = bitMask + "," + cur;
		if(map.containsKey(key)){
			return map.get(key);
		}
		int max = dfs(words, bitMask, cur + 1, count, score);
		int[] cnt = count.clone();
		boolean valid = true;
		int s = 0;
		for(char c : words[cur].toCharArray()){
			if(cnt[c - 'a'] < 1){
				valid = false;
				break;
			}
			cnt[c -'a']--;
			s += score[c - 'a'];
		}
		if(valid){
			int newBitMask = bitMask | (1 << cur);
			max = Math.max(max, s + dfs(words, newBitMask, cur + 1, cnt, score));
		}
		map.put(key, max);
		return max;
	}
}
