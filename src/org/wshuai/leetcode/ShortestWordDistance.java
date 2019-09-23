package org.wshuai.leetcode;

/**
 * Created by Wei on 10/2/16.
 * #243 https://leetcode.com/problems/shortest-word-distance/
 */
public class ShortestWordDistance {
	public int shortestDistance(String[] words, String word1, String word2) {
		int min = Integer.MAX_VALUE;
		int w1 = -1;
		int w2 = -1;
		int len = words.length;
		for (int i = 0; i < len; i++) {
			String val = words[i];
			if (val.equals(word1)) {
				w1 = i;
				if (w2 != -1) {
					int dis = w1 - w2;
					min = dis < min ? dis : min;
				}
			}
			if (val.equals(word2)) {
				w2 = i;
				if (w1 != -1) {
					int dis = w2 - w1;
					min = dis < min ? dis : min;
				}
			}
		}
		return min;
	}
}
