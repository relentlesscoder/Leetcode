package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/23/19.
 * #1297 https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/
 */
public class MaximumNumberOfOccurrencesOfASubstring {
	// https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/discuss/457577/C%2B%2B-Greedy-approach-%2B-Sliding-window-O(n).
	public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
		int start = 0;
		int res = 0;
		int[] count = new int[26];
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < s.length(); i++){
			count[s.charAt(i) - 'a']++;
			if(i - start + 1 > minSize){
				--count[s.charAt(start) - 'a'];
				start++;
			}
			if(i - start + 1 == minSize && countCharacters(count) <= maxLetters){
				String str = s.substring(start, i + 1);
				int cnt = map.getOrDefault(str, 0) + 1;
				map.put(str, cnt);
				res = Math.max(res, cnt);
			}
		}
		return res;
	}

	private int countCharacters(int[] arr){
		int res = 0;
		for(int i = 0; i < 26; i++){
			if(arr[i] > 0){
				res++;
			}
		}
		return res;
	}
}
