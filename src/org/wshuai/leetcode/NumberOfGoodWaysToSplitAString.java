package org.wshuai.leetcode;

/**
 * Created by Wei on 07/25/2020.
 * #1525 https://leetcode.com/problems/number-of-good-ways-to-split-a-string/
 */
public class NumberOfGoodWaysToSplitAString {

	// time O(n), space O(n)
	public int numSplits(String s) {
		int res = 0, cur = 0, n = s.length();
		int[] count = new int[26];
		// stores distinct character count from
		// right side of the current index
		int[] distRight = new int[n];
		for(int i = n - 1; i >= 0; i--){
			distRight[i] = cur;
			int index = s.charAt(i) - 'a';
			if(count[index] == 0){
				cur++;
			}
			count[index]++;
		}
		cur = 0;
		count = new int[26];
		for(int i = 0; i < n - 1; i++){
			int index = s.charAt(i) - 'a';
			if(count[index] == 0){
				cur++;
			}
			count[index]++;
			if(cur == distRight[i]){
				res++;
			}
		}
		return res;
	}
}
