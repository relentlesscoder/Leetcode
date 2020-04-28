package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #0791 https://leetcode.com/problems/custom-sort-string/
 */
public class CustomSortString {

	// time O(m+n)
	public String customSortString(String S, String T) {
		StringBuilder res = new StringBuilder();
		int[] count = new int[26];
		for(char c : T.toCharArray()){
			count[c - 'a']++;
		}
		for(char c : S.toCharArray()){
			while(count[c - 'a']-- > 0){
				res.append(c);
			}
		}
		for(int i = 0; i < 26; i++){
			char c = (char)(i + 'a');
			while(count[i]-- > 0){
				res.append(c);
			}
		}
		return res.toString();
	}
}
