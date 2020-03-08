package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2016.
 * #0244 https://leetcode.com/problems/shortest-word-distance-iii/
 */
public class ShortestWordDistanceIII {
	public int shortestWordDistance(String[] words, String word1, String word2) {
		int dist = Integer.MAX_VALUE, n = words.length, w1 = -n, w2 = -n;
		boolean same = word1.equals(word2), set = false;
		for(int i = 0; i < words.length; i++){
			// if word1 and word2 are same, only set w1 when w1 is not set
			if(words[i].equals(word1) && (!same || !set)){
				w1 = i;
				dist = Math.min(dist, w1 - w2);
				set = true;
			// 	if word1 and word2 are same, only set w2 when w1 is already set
			}else if(words[i].equals(word2) && (!same || set)){
				w2 = i;
				dist = Math.min(dist, w2 - w1);
				set = false;
			}
		}
		return dist;
	}
}
