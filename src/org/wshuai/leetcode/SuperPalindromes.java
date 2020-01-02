package org.wshuai.leetcode;

/**
 * Created by Wei on 1/2/2020.
 * #906 https://leetcode.com/problems/super-palindromes/
 */
public class SuperPalindromes {
	//https://leetcode.com/problems/super-palindromes/discuss/373544/Java-4-ms-Easy-DFS
	public int superpalindromesInRange(String L, String R) {
		int low = (int)Math.ceil(Math.sqrt(Long.parseLong(L)));
		int high = (int)Math.floor(Math.sqrt(Long.parseLong(R)));

		int ret = (3 >= low && 3 <= high) ? 1 : 0;
		ret += dfs(low, high, "");
		ret += dfs(low, high, "0");
		ret += dfs(low, high, "1");
		ret += dfs(low, high, "2");

		return ret;
	}

	private int dfs(int low, int high, String s) {
		if (s.length() > Integer.toString(high).length()) return 0;

		int count = 0;

		if (!s.isEmpty() && s.charAt(0) != '0') {
			long num = Long.parseLong(s);
			if (num > high) return 0;
			if (num >= low && isPalindrom(num * num)) count++;
		}

		for (char c = '0'; c <= '2'; c++) {
			count += dfs(low, high, c + s + c);
		}

		return count;
	}

	private boolean isPalindrom(long num) {
		String s = Long.toString(num);
		return s.equals(new StringBuilder(s).reverse().toString());
	}
}
