package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2019.
 * #1163 https://leetcode.com/problems/last-substring-in-lexicographical-order/
 */
public class LastSubstringInLexicographicalOrder {
	public String lastSubstring(String s) {
		int i = 0, j = 1, k = 0;
		int n = s.length();
		char[] arr = s.toCharArray();
		while (j + k < n){
			if(arr[i + k] == arr[j + k]){
				k += 1;
				continue;
			}else if(arr[i + k] > arr[j + k]){
				j = j + k + 1;
			}else{
				i = j;
				j = i + 1;
			}
			k = 0;
		}
		return s.substring(i);
	}
}
