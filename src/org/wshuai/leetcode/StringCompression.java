package org.wshuai.leetcode;

/**
 * Created by Wei on 09/18/2019.
 * #0443 https://leetcode.com/problems/string-compression/
 */
public class StringCompression {
	// time O(n)
	public int compress(char[] chars) {
		if(chars == null || chars.length == 0){
			return 0;
		}
		int i = 0, count = 1;
		char cur = chars[0];
		for(int j = 1; j < chars.length; j++){
			if(chars[j] != cur){
				chars[i++] = cur;
				if(count > 1){
					for(char d : Integer.toString(count).toCharArray()){
						chars[i++] = d;
					}
				}
				cur = chars[j];
				count = 1;
			}else{
				count++;
			}
		}
		chars[i++] = cur;
		if(count > 1){
			for(char d : Integer.toString(count).toCharArray()){
				chars[i++] = d;
			}
		}
		return i;
	}
}
