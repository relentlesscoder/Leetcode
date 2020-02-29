package org.wshuai.leetcode;

/**
 * Created by Wei on 02/26/2017.
 * #0482 https://leetcode.com/problems/license-key-formatting/
 */
public class LicenseKeyFormatting {
	// time O(n)
	public String licenseKeyFormatting(String S, int K) {
		int dist = 'a' - 'A';
		StringBuilder sb = new StringBuilder();
		char[] arr = S.toCharArray();
		for(int i = arr.length - 1, j = 0; i >= 0; i--){
			char c = arr[i];
			if(c == '-'){
				continue;
			}
			if(c >= 'a' && c <= 'z'){
				c = (char)(c - dist);
			}
			sb.append(c);
			j++;
			if(j == K){
				sb.append("-");
				j = 0;
			}
		}
		if(sb.length() > 0 && sb.charAt(sb.length() - 1) == '-'){
			return sb.reverse().substring(1);
		}
		return sb.reverse().toString();
	}
}
