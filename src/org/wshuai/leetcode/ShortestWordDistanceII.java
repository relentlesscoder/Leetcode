package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/11/2016.
 * #0244 https://leetcode.com/problems/shortest-word-distance-ii/
 */
public class ShortestWordDistanceII {
	private Map<String, List<Integer>> map;

	public ShortestWordDistanceII(String[] words) {
		map = new HashMap<>();
		for(int i = 0; i < words.length; i++){
			String cur = words[i];
			map.putIfAbsent(cur, new ArrayList<>());
			map.get(cur).add(i);
		}
	}

	// time O(n), space O(n)
	public int shortest(String word1, String word2) {
		List<Integer> list1 = map.get(word1);
		List<Integer> list2 = map.get(word2);
		int res = Integer.MAX_VALUE, i = 0, j = 0;
		while(i < list1.size() && j < list2.size()){
			int w1 = list1.get(i), w2 = list2.get(j);
			if(w1 < w2){
				res = Math.min(res, w2 - w1);
				i++;
			}else{
				res = Math.min(res, w1 - w2);
				j++;
			}
		}
		return res;
	}
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
