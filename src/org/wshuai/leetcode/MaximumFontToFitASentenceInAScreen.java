package org.wshuai.leetcode;

/**
 * Created by Wei on 10/17/2020.
 * #1618 https://leetcode.com/problems/maximum-font-to-fit-a-sentence-in-a-screen/
 */
public class MaximumFontToFitASentenceInAScreen {

	// time O(log(n))
	public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {
		int left = 0, right = fonts.length - 1;
		while (left < right) {
			int mid = left + (right - left + 1) / 2;
			if (fontInfo.getHeight(fonts[mid]) > h
				|| calculateWidth(text, fonts[mid], fontInfo) > w) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		return fontInfo.getHeight(fonts[left]) <= h
			&& calculateWidth(text, fonts[left], fontInfo) <= w ? fonts[left] : -1;
	}

	private int calculateWidth(String text, int fontSize, FontInfo fontInfo) {
		int size = 0;
		for (char c : text.toCharArray()) {
			size += fontInfo.getWidth(fontSize, c);
		}
		return size;
	}


	// This is the FontInfo's API interface.
	// You should not implement it, or speculate about its implementation
	private interface FontInfo {
		// Return the width of char ch when fontSize is used.
		public int getWidth(int fontSize, char ch);

		// Return Height of any char when fontSize is used.
		public int getHeight(int fontSize);
	}
}
