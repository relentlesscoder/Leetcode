package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0171 https://leetcode.com/problems/excel-sheet-column-number/
 */
public class ExcelSheetColumnNumber {
	// time O(n)
	public int titleToNumber(String s) {
		if(s == null || s.isEmpty()){
			return 0;
		}
		int res = 0, n = s.length();
		for(int i = 0; i < n; i++){
			res = res * 26 + (s.charAt(i) - 'A') + 1;
		}
		return res;
	}
}
