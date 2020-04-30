package org.wshuai.leetcode;

/**
 * Created by Wei on 08/22/2019.
 * #0806 https://leetcode.com/problems/number-of-lines-to-write-string/
 */
public class NumberOfLinesToWriteString {
	// time O(n)
	public int[] numberOfLines(int[] widths, String S) {
		int row = 0, count = 0;
		for(char c : S.toCharArray()){
			if(count + widths[c - 'a'] > 100){
				row++;
				count = widths[c - 'a'];
			}else{
				count += widths[c - 'a'];
			}
		}
		return new int[]{row + 1, count};
	}
}
