package org.wshuai.leetcode;

/**
 * Created by Wei on 03/19/2017.
 * #0306 https://leetcode.com/problems/additive-number/
 */
public class AdditiveNumber {
	public boolean isAdditiveNumber(String num) {
		if (num == null || num.length() < 3) {
			return false;
		}
		int n = num.length();
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				if ((num.charAt(0) == '0' && i > 0)
						|| (num.charAt(i + 1) == '0' && j > i + 1)) {
					continue;
				}
				String s = num.substring(0, i + 1);
				String t = num.substring(i + 1, j + 1);
				if (dfs(s, t, j + 1, num)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean dfs(String s, String t, int start, String num) {
		if (start == num.length()) {
			return true;
		}
		String sum = getSum(s, t);
		int n = num.length(), len = sum.length(), next = start + len;
		if (next > n || !sum.equals(num.substring(start, next))) {
			return false;
		}
		return dfs(t, sum, next, num);
	}

	// add two strings
	// #0415 https://leetcode.com/problems/add-strings/
	private String getSum(String s, String t) {
		StringBuilder sb = new StringBuilder();
		char[] rs = new StringBuilder(s).reverse().toString().toCharArray();
		char[] rt = new StringBuilder(t).reverse().toString().toCharArray();
		int i = 0, j = 0, c = 0;
		while (i < rs.length || j < rt.length || c > 0) {
			int v1 = i < rs.length ? rs[i++] - '0' : 0;
			int v2 = j < rt.length ? rt[j++] - '0' : 0;
			int sum = v1 + v2 + c;
			sb.append(sum % 10);
			c = sum / 10;
		}
		return sb.reverse().toString();
	}
}
