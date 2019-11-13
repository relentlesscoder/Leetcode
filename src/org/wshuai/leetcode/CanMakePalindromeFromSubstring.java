package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/13/2019.
 * #1177 https://leetcode.com/problems/can-make-palindrome-from-substring/
 */
public class CanMakePalindromeFromSubstring {
	public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
		List<Boolean> ans = new ArrayList<>();
		// save count of each character for all prefix
		int[][] cnt = new int[s.length() + 1][26];
		for (int i = 0; i < s.length(); ++i) {
			cnt[i + 1] = cnt[i].clone();
			++cnt[i + 1][s.charAt(i) - 'a'];
		}
		for (int[] q : queries) {
			int sum = 0;
			for (int i = 0; i < 26; ++i) {
				sum += (cnt[q[1] + 1][i] - cnt[q[0]][i]) % 2;
			}
			ans.add(sum / 2 <= q[2]);
		}
		return ans;
	}
}
