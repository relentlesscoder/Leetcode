package org.wshuai.leetcode;

/**
 * Created by Wei on 03/08/2020.
 * #1374 https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/
 */
public class GenerateAStringWithCharactersThatHaveOddCounts {
	// time O(n), space O(n)
	public String generateTheString(int n) {
		char[] res = new char[n];
		int i = 0;
		if(n % 2 == 0){
			res[i++] = 'z';
		}
		while(i < n){
			res[i++] = 'a';
		}
		return new String(res);
	}
}
