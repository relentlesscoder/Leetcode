package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 02/24/2021.
 * #1772 https://leetcode.com/problems/sort-features-by-popularity/
 */
public class SortFeaturesByPopularity {

	// time O(n*log(n)), space O(n)
	public String[] sortFeatures(String[] features, String[] responses) {
		Map<String, int[]> count = new HashMap<>();
		for(int i = 0; i < features.length; i++){
			count.put(features[i], new int[]{i, 0});
		}
		for(int i = 0; i < responses.length; i++){
			Set<String> words = new HashSet<>();
			for(String s : responses[i].split("\\s")){
				words.add(s);
			}
			for(String w : words){
				if(count.containsKey(w)){
					count.get(w)[1]++;
				}
			}
		}
		Arrays.sort(features, (a, b) -> {
			int[] arrA = count.get(a), arrB = count.get(b);
			return arrA[1] == arrB[1] ? arrA[0] - arrB[0] : arrB[1] - arrA[1];
		});
		return features;
	}
}
