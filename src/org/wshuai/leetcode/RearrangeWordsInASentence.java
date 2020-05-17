package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 05/17/2020.
 * #1451 https://leetcode.com/problems/rearrange-words-in-a-sentence/
 */
public class RearrangeWordsInASentence {

	// time O(n*log(n))
	public String arrangeWords(String text) {
		int diff = 'a' - 'A';
		text = (char)(text.charAt(0) + diff) +  text.substring(1);
		String[] arr = text.split("\\s");
		Arrays.sort(arr, (a, b) -> a.length() - b.length());
		String temp = String.join(" ", arr);
		return (char)(temp.charAt(0) - diff) +  temp.substring(1);
	}
}
