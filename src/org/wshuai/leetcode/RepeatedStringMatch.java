package org.wshuai.leetcode;

/**
 * Created by Wei on 10/09/2019.
 * #0686 https://leetcode.com/problems/repeated-string-match/
 */
public class RepeatedStringMatch {
	// time O(m*n)
	public int repeatedStringMatch(String A, String B) {
		int count = 0;
		StringBuilder sb = new StringBuilder();
		while(sb.length() < B.length()){
			sb.append(A);
			count++;
		}
		if(sb.toString().contains(B)){
			return count;
		}
		// handle rotation
		if(sb.append(A).toString().contains(B)){
			return count + 1;
		}
		return -1;
	}
}
