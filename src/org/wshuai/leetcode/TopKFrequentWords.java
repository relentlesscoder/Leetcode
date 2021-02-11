package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/06/2019.
 * #0692 https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {

	// time O(n*log(k)), space O(n)
	// https://leetcode.com/problems/top-k-frequent-words/discuss/431008/Summary-of-all-the-methods-you-can-imagine-of-this-problem
	public List<String> topKFrequent(String[] words, int k) {
		List<String> res = new ArrayList<>();
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) ->
			a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
		Map<String, Integer> map = new HashMap<>();
		for(String word : words){
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			pq.offer(entry);
		}
		while(k-- > 0 && !pq.isEmpty()){
			res.add(pq.poll().getKey());
		}
		return res;
	}
}
