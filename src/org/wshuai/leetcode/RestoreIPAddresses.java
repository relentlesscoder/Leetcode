package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/10/2016.
 * #0093 https://leetcode.com/problems/restore-ip-addresses/
 */
public class RestoreIPAddresses {

	// time O(n^4), space O(n)
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<>();
		dfs(0, 0, new String[4], s, res);
		return res;
	}

	private void dfs(int i, int p, String[] ip, String s, List<String> res) {
		if (i == s.length()) { // 所有字符处理完
			if (p == 4) { // 且能被分成四部分
				res.add(String.join(".", ip));
			}
			return;
		}
		if (p >= 4) { // 已经分成四部分但是字符还没有处理完，提前结束
			return;
		}
		for (int j = i; j < Math.min(i + 3, s.length()); j++) {
			String str = s.substring(i, j + 1);
			if (str.length() == 2 && str.charAt(0) == '0') { // 子串含前缀 0 不符合要求
				break;
			}
			if (Integer.parseInt(str) > 255) { // 子串大于 255
				break;
			}
			ip[p] = str;
			dfs(j + 1, p + 1, ip, s, res);
		}
	}
}
