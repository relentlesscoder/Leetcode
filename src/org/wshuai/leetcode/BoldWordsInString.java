package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/19.
 * #758 https://leetcode.com/problems/bold-words-in-string/
 */
public class BoldWordsInString {
	public String boldWords(String[] words, String S) {
		int len = S.length();
		if(len == 0){
			return "";
		}
		int[] mask = new int[len];
		int i = 0;
		while(i < len){
			for(String word: words){
				int m = i;
				int n = 0;
				while(m < len && n < word.length() && S.charAt(m) == word.charAt(n)){
					m++;
					n++;
				}
				if(n == word.length()){
					for(int k = i; k < m; k++){
						mask[k] = 1;
					}
				}
			}
			i++;
		}
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < len; j++){
			if(mask[j] == 1 && (j == 0 || mask[j-1] == 0)){
				sb.append("<b>");
			}
			sb.append("" + S.charAt(j));
			if(mask[j] == 1 && (j == len-1 || mask[j+1] == 0)){
				sb.append("</b>");
			}
		}
		return sb.toString();
	}
}
