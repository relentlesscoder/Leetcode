package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2016.
 * #0243 https://leetcode.com/problems/shortest-word-distance/
 */
public class ShortestWordDistance {
	// time O(n)
	public int shortestDistance(String[] words, String word1, String word2) {
		int dist = Integer.MAX_VALUE, n = words.length, w1 = -n, w2 = -n;
		for(int i = 0; i < words.length; i++){
			if(words[i].equals(word1)){
				w1 = i;
				dist = Math.min(dist, w1 - w2);
			}
			if(words[i].equals(word2)){
				w2 = i;
				dist = Math.min(dist, w2 - w1);
			}
		}
		return dist;
	}
}
