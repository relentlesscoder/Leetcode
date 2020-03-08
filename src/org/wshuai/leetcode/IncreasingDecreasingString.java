package org.wshuai.leetcode;

/**
 * Created by Wei on 03/08/2020.
 * #1370 https://leetcode.com/problems/increasing-decreasing-string/
 */
public class IncreasingDecreasingString {
	// time O(n)
	public String sortString(String s) {
		StringBuilder sb = new StringBuilder();
		int[] count = new int[26];
		for(char c : s.toCharArray()){
			count[c - 'a']++;
		}
		int n = s.length(), i = 0;
		while(sb.length() < n){
			while(i < 26){
				if(count[i] > 0){
					sb.append((char)(i + 'a'));
					count[i]--;
				}
				i++;
			}
			i--;
			while(i >= 0){
				if(count[i] > 0){
					sb.append((char)(i + 'a'));
					count[i]--;
				}
				i--;
			}
			i++;
		}
		return sb.toString();
	}
}
