package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2016.
 * #0067 https://leetcode.com/problems/add-binary/
 */
public class AddBinary {

	// time O(n)
	public String addBinary(String a, String b) {
		StringBuilder res = new StringBuilder();
		for(int i = a.length() - 1, j = b.length() - 1, k = 0; i >= 0 || j >= 0 || k > 0; i--, j--){
			int x = i >= 0 ? a.charAt(i) - '0' : 0;
			int y = j >= 0 ? b.charAt(j) - '0' : 0;
			int s = (x + y + k);
			res.append((char)(s % 2 + '0'));
			k = s >= 2 ? 1 : 0;
		}
		return res.reverse().toString();
	}
}
