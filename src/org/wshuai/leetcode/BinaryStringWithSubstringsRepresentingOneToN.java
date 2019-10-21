package org.wshuai.leetcode;

/**
 * Created by Wei on 10/18/2019.
 * #1016 https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/
 */
public class BinaryStringWithSubstringsRepresentingOneToN {
	// https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/discuss/260847/JavaC%2B%2BPython-O(S)
	public boolean queryString(String S, int N) {
		for(int i = N; i > N / 2; i--){
			if(!S.contains(Integer.toBinaryString(i))){
				return false;
			}
		}
		return true;
	}
}
