package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2019.
 * #984 https://leetcode.com/problems/string-without-aaa-or-bbb/
 */
public class StringWithoutAAAOrBBB {
	public String strWithout3a3b(int A, int B) {
		StringBuilder sb = new StringBuilder();
		while(A > 0 || B > 0){
			boolean writeA = false;
			int len = sb.length();
			if(len >= 2 && sb.charAt(len - 1) == sb.charAt(len - 2)){
				if(sb.charAt(len - 1) == 'b'){
					writeA = true;
				}
			}else{
				writeA = A >= B;
			}

			if(writeA){
				A--;
				sb.append('a');
			}else{
				B--;
				sb.append('b');
			}
		}
		return sb.toString();
	}
}
