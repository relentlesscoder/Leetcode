package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/14/2019.
 * #737 https://leetcode.com/problems/sentence-similarity-ii/
 */
public class SentenceSimilarityII {
	private Map<String, String> map;

	// union find
	public boolean areSentencesSimilarTwo(String[] words1,
	                                      String[] words2, List<List<String>> pairs) {
		if(words1.length != words2.length){
			return false;
		}
		map = new HashMap<>();
		for(List<String> p: pairs){
			String w1 = p.get(0);
			String w2 = p.get(1);
			String r1 = findRoot(w1);
			String r2 = findRoot(w2);
			if(!r1.equals(r2)){
				map.put(r2, r1);
			}
		}
		for(int i = 0; i < words1.length; i++){
			String w1 = words1[i];
			String w2 = words2[i];
			String r1 = findRoot(w1);
			String r2 = findRoot(w2);
			if(!r1.equals(r2)){
				return false;
			}
		}
		return true;
	}

	private String findRoot(String word){
		if(!map.containsKey(word)){
			map.put(word, word);
		}
		while(map.get(word) != word){
			word = map.get(word);
		}
		return word;
	}
}
