package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2114 https://leetcode.com/problems/maximum-number-of-words-found-in-sentences/
 */
public class MaximumNumberOfWordsFoundInSentences {

	// time O(n * m), space O(1)
	public int mostWordsFound(String[] sentences) {
		int max = 0;
		for (String sentence : sentences) {
			int count = 0;
			for (char c : sentence.toCharArray()) {
				if (c == ' ') {
					count++;
				}
			}
			max = Math.max(max, count + 1);
		}
		return max;
	}
}
