package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/08/2020.
 * #1371 https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
 */
public class FindTheLongestSubstringContainingVowelsInEvenCounts {

	private Map<Character, Integer> vowels = new HashMap<Character, Integer>(){
		{
			put('a', 0);
			put('e', 1);
			put('i', 2);
			put('o', 3);
			put('u', 4);
		}
	};

	// time O(n)
	public int findTheLongestSubstring(String s) {
		Map<Integer, Integer> stateToIndex = new HashMap<>();
		stateToIndex.put(0, -1);
		int state = 0, res = 0;
		for(int i = 0; i < s.length(); i++){
			char cur = s.charAt(i);
			int digit = vowels.getOrDefault(cur, -1);
			if(digit != -1){
				state ^= (1 << digit);
			}
			stateToIndex.putIfAbsent(state, i);
			res = Math.max(res, i - stateToIndex.get(state));
		}
		return res;
	}
}
