package org.wshuai.leetcode;

/**
 * Created by Wei on 05/24/2020.
 * #1455 https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
 */
public class CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {

	// time O(n)
	public int isPrefixOfWord(String sentence, String searchWord) {
		int m = sentence.length(), n = searchWord.length();
		boolean flag = true;
		for(int i = 0, j = 0, k = 0; i < m; i++){
			char c = sentence.charAt(i);
			if(c == ' '){
				flag = true;
				j = 0;
				k++;
			}else if(flag && c == searchWord.charAt(j)){
				if(j++ == n - 1){
					return k + 1;
				}
			}else{
				flag = false;
			}
		}
		return -1;
	}
}
