package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0318 https://leetcode.com/problems/maximum-product-of-word-lengths/
 */
public class MaximumProductOfWordLengths {
	// time O(n^2), space O(n)
	public int maxProduct(String[] words) {
		if(words == null || words.length == 0){
			return 0;
		}
		int res = 0, n = words.length;
		int[] map = new int[n];
		for(int i = 0; i < n; i++){
			char[] arr = words[i].toCharArray();
			for(char c : arr){
				map[i] |= 1 << (c - 'a');
			}
		}
		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				if((map[i] & map[j]) != 0){
					continue;
				}
				res = Math.max(res, words[i].length()
						* words[j].length());
			}
		}
		return res;
	}
}
