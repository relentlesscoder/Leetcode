package org.wshuai.leetcode;

/**
 * Created by Wei on 09/18/2019.
 * #0443 https://leetcode.com/problems/string-compression/
 */
public class StringCompression {

	// time O(n)
	public int compress(char[] chars) {
		int n = chars.length, i = 0;
		for(int j = 0, count = 0; j < n; j++){
			count++;
			if(j == n - 1 || chars[j + 1] != chars[j]){
				chars[i++] = chars[j];
				if(count > 1){
					for(char c : Integer.toString(count).toCharArray()){
						chars[i++] = c;
					}
				}
				count = 0;
			}
		}
		return i;
	}
}
