package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/11/2016.
 * #244 https://leetcode.com/problems/shortest-word-distance-ii/
 */
public class ShortestWordDistanceII {
	private Map<String, List<Integer>> pMap;
	private String[] words;

	public ShortestWordDistanceII(String[] words) {
		this.pMap = new HashMap<String, List<Integer>>();
		this.words = words;
		int len = words.length;
		for (int i = 0; i < len; i++) {
			String val = words[i];
			if (pMap.containsKey(val)) {
				List<Integer> ls = pMap.get(val);
				ls.add(i);
			} else {
				List<Integer> ls = new ArrayList<Integer>();
				ls.add(i);
				pMap.put(val, ls);
			}
		}
	}

	public int shortest(String word1, String word2) {
		List<Integer> lst1 = pMap.get(word1);
		List<Integer> lst2 = pMap.get(word2);

		int min = Integer.MAX_VALUE;
		int p1 = 0;
		int p2 = 0;
		int len1 = lst1.size();
		int len2 = lst2.size();
		while (p1 < len1 && p2 < len2) {
			int idx1 = lst1.get(p1);
			int idx2 = lst2.get(p2);
			min = Math.min(min, Math.abs(idx1 - idx2));
			if (idx1 < idx2) {
				p1++;
			} else {
				p2++;
			}
		}
		return min;
	}
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
