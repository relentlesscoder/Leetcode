package org.wshuai.leetcode;

/**
 * Created by Wei on 11/8/19.
 * #880 https://leetcode.com/problems/decoded-string-at-index/
 */
public class DecodedStringAtIndex {
	public String decodeAtIndex(String S, int K) {
		long N = 0;
		int i = 0;
		for(; N < K; i++){
			N = Character.isDigit(S.charAt(i)) ? N * (S.charAt(i) - '0') : N + 1;
		}
		while(i-- > 0){
			if(Character.isDigit(S.charAt(i))){
				N /= (S.charAt(i) - '0');
				K %= N;
			}else if(K % N-- == 0){
				return "" + S.charAt(i);
			}
		}
		return "";
	}
}
