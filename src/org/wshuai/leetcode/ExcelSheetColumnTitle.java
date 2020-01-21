package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0168 https://leetcode.com/problems/excel-sheet-column-title/
 */
public class ExcelSheetColumnTitle {
	// time O(log(n))
	public String convertToTitle(int n) {
		StringBuilder res = new StringBuilder();
		while(n > 0){
			n--;
			int rem = n % 26;
			res.append((char)(rem + 'A'));
			n /= 26;
		}
		return res.reverse().toString();
	}
}
