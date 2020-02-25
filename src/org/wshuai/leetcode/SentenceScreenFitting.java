package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2016.
 * #0418 https://leetcode.com/problems/sentence-screen-fitting/
 */
public class SentenceScreenFitting {
	// time O(rows * n)
	// https://leetcode.com/problems/sentence-screen-fitting/discuss/90845/21ms-18-lines-Java-solution
	public int wordsTyping(String[] sentence, int rows, int cols) {
		if (sentence == null || sentence.length == 0 || rows <= 0 || cols <= 0) {
			return 0;
		}
		int start = 0;
		StringBuilder sb = new StringBuilder();
		for (String s : sentence) {
			sb.append(s + " ");
		}
		int n = sb.length();
		for (int i = 0; i < rows; i++) {
			start += cols;
			if (sb.charAt(start % n) == ' ') {
				// if next character is a space, then we need
				// to add the 1 space back to actual string
				// length since the space will be skipped
				// in the screen string due to line change.
				start++;
			} else {
				// if next character is not a space, then we
				// are in the middle of a character, move the
				// cursor back to the beginning of the current
				// word.
				while (start > 0 && sb.charAt((start - 1) % n) != ' ') {
					start--;
				}
			}
		}
		return start / n;
	}
}
