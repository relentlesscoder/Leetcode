package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/18/2020.
 * #1324 https://leetcode.com/problems/print-words-vertically/
 */
public class PrintWordsVertically {
	// time O(n)
	public List<String> printVertically(String s) {
		List<String> res = new ArrayList<>();
		String[] vals = s.split(" ");
		int n = 0;
		for(String v : vals){
			n = Math.max(v.length(), n);
		}
		for(int i = 0; i < n; i++){
			StringBuilder sb = new StringBuilder();
			for(String v : vals){
				sb.append(i >= v.length() ? ' ' : v.charAt(i));
			}
			int j = sb.length() - 1;
			while(j >= 0 && sb.charAt(j) == ' '){
				j--;
			}
			res.add(sb.substring(0, j + 1));
		}
		return res;
	}
}
