package org.wshuai.leetcode;

/**
 * Created by Wei on 9/25/19.
 * #567 https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString {

	// sliding window
	public boolean checkInclusion(String s1, String s2) {
		if(s2.length() < s1.length()){
			return false;
		}
		int[] c1 = new int[26];
		for(char c: s1.toCharArray()){
			c1[c-'a']++;
		}
		int i = 0;
		char[] c2 = s2.toCharArray();
		while(i < c2.length){
			if(c1[c2[i]-'a'] == 0){
				i++;
				continue;
			}
			int[] tmp = new int[26];
			for(int k = 0; k < 26; k++){
				tmp[k] = c1[k];
			}
			int j = i;
			while(j < c2.length && tmp[c2[j]-'a'] > 0){
				tmp[c2[j]-'a']--;
				j++;
			}
			if(j - i == s1.length()){
				return true;
			}
			// example: s1 - abcdac s2 - exqybacaacbd
			// when the first attempt to match (from index 4, character b) reaches index 8, character a
			// update the starting point to index 6 try to match it again
			if(j < c2.length && c1[c2[j]-'a'] > 0){
				while(i < j && c2[i] != c2[j]){
					i++;
				}
				while(i < j && c2[i] == c2[j]){
					i++;
				}
			}else{
				i = j;
			}
		}
		return false;
	}
}
