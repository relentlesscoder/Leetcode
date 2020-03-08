package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 10/25/2016.
 * #0422 https://leetcode.com/problems/valid-word-square/
 */
public class ValidWordSquare {
	// time O(m*n)
	public boolean validWordSquare(List<String> words) {
		int m = words.size();
		for(int i = 0; i < m; i++){
			String word = words.get(i);
			for(int j = 0; j < word.length(); j++){
				if(m <= j || words.get(j).length() <= i || word.charAt(j) != words.get(j).charAt(i)){
					return false;
				}
			}
		}
		return true;
	}
}
