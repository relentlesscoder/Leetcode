package org.wshuai.leetcode;

/**
 * Created by Wei on 10/14/2019.
 * #0616 https://leetcode.com/problems/add-bold-tag-in-string/
 */
public class AddBoldTagInString {
	// time O(n*k), space O(n)
	public String addBoldTag(String s, String[] dict) {
		int n = s.length();
		boolean[] bold = new boolean[n];
		for(String word : dict){
			int start = 0, index = 0, len = word.length();
			while((index = s.indexOf(word, start)) != -1){
				for(int i = index; i < index + len; i++){
					bold[i] = true;
				}
				start = index + 1;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0, j = 0; i < n; i++){
			if(!bold[i]){
				sb.append(s.charAt(i));
				continue;
			}
			j = i;
			while(j < n && bold[j]){
				j++;
			}
			sb.append("<b>" + s.substring(i, j) + "</b>");
			i = j - 1;
		}
		return sb.toString();
	}
}
