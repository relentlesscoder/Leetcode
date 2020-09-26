package org.wshuai.leetcode;

/**
 * Created by Wei on 10/14/2019.
 * #0616 https://leetcode.com/problems/add-bold-tag-in-string/
 */
public class AddBoldTagInString {

	// time O(d*n^2), space O(n)
	public String addBoldTag(String s, String[] dict) {
		int n = s.length();
		boolean[] bold = new boolean[n];
		for(String word : dict){
			int i = 0, j;
			while((j = s.indexOf(word, i)) >= 0){
				for(int k = 0; k < word.length(); k++){
					bold[j + k] = true;
				}
				// advance one position to match entire "zzz" using pattern "zz"
				i = j + 1;
			}
		}
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < n; i++){
			if(bold[i] && (i == 0 || !bold[i - 1])){
				res.append("<b>");
			}
			res.append(s.charAt(i));
			if(bold[i] && (i == n - 1 || !bold[i + 1])){
				res.append("</b>");
			}
		}
		return res.toString();
	}
}
