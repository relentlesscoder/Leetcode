package org.wshuai.leetcode;

/**
 * Created by Wei on 08/12/2016.
 * #0006 https://leetcode.com/problems/zigzag-conversion/
 */
public class ZigZagConversion {
	// time O(n)
	public String convert(String s, int numRows) {
		if(numRows <= 1){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		int size = 2 * numRows - 2;
		for(int i = 0; i < numRows; i++){
			for(int j = i; j < s.length(); j += size){
				sb.append(s.charAt(j));
				if(i != 0 && i != numRows - 1){
					int index = j + size - 2 * i;
					if(index < s.length()){
						sb.append(s.charAt(index));
					}
				}
			}
		}
		return sb.toString();
	}
}
