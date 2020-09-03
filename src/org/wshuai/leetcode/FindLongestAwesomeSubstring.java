package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/03/2020.
 * #1542 https://leetcode.com/problems/find-longest-awesome-substring/
 */
public class FindLongestAwesomeSubstring {

	// time O(n)
	public int longestAwesome(String s) {
		int res = 0, n = s.length(), cur = 0;
		// use 10 bits bitmask to represent [0 ... 9]
		int[] prefix = new int[1_024];
		Arrays.fill(prefix, n);
		prefix[0] = -1;
		for(int i = 0; i < n; i++){
			cur ^= (1 << (s.charAt(i) - '0'));
			// for palindrome like 1112111
			for(int j = 0; j < 10; j++){
				res = Math.max(res, i - prefix[cur ^ (1 << j)]);
			}
			// for palindrome like 11222211
			res = Math.max(res, i - prefix[cur]);
			prefix[cur] = Math.min(prefix[cur], i);
		}
		return res;
	}
}
