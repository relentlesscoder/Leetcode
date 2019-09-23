package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #171 https://leetcode.com/problems/excel-sheet-column-number/
 */
public class ExcelSheetColumnNumber {
	public int titleToNumber(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}

		int len = s.length();
		char[] arr = s.toCharArray();
		int val = 0;
		int ct = 1;
		for (int i = len - 1; i >= 0; i--) {
			char c = arr[i];
			int v = c - 64;
			v *= ct;
			val += v;
			ct *= 26;
		}

		return val;
	}
}
