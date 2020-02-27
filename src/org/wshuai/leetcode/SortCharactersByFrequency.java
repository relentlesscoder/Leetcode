package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/03/2016.
 * #0451 https://leetcode.com/problems/sort-characters-by-frequency/
 */
public class SortCharactersByFrequency {
	// time O(n*log(n))
	public String frequencySort(String s) {
		if(s == null || s.isEmpty()){
			return "";
		}
		StringBuilder res = new StringBuilder();
		int[][] freq = new int[256][2];
		for(char c : s.toCharArray()){
			freq[c][0]++;
		}
		for(int i = 0; i < 256; i++){
			freq[i][1] = i;
		}
		Arrays.sort(freq, (a, b) -> b[0] - a[0]);
		for(int i = 0; i < 256; i++){
			char val = (char)freq[i][1];
			while(freq[i][0]-- > 0){
				res.append(val);
			}
		}
		return res.toString();
	}
}
