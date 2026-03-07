package org.wshuai.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2016.
 * #0131 https://leetcode.com/problems/palindrome-partitioning/
 */
public class PalindromePartitioning {

    // time O(n * 2^n), space O(n)
    public List<List<String>> partition(String s) {
		// 选或者不选
        List<List<String>> res = new ArrayList<>();
        dfs(0, 0, s, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int i, int start, String s, List<String> path, List<List<String>> res) {
        if (i == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
		// 不选
        if (i < s.length() - 1) { // 注意 i == s.length() - 1 的时候必须选
            dfs(i + 1, start, s, path, res);
        }
        if (isPalindrome(start, i, s)) { // 如果当前子串是回文，则选之
            path.add(s.substring(start, i + 1));
            dfs(i + 1, i + 1, s, path, res);
            path.remove(path.size() - 1);
        }
    }

	// time O(n * 2^n), space O(n)
	public List<List<String>> partition2(String s) {
		// 枚举下一个分割点
		List<List<String>> res = new ArrayList<>();
		dfs2(0, s, new ArrayList<>(), res);
		return res;
	}

	private void dfs2(int i, String s, List<String> path, List<List<String>> res) {
		if (i == s.length()) {
			res.add(new ArrayList<>(path));
			return;
		}
		for (int j = i; j < s.length(); j++) {
			if (isPalindrome(i, j, s)) {
				path.add(s.substring(i, j + 1));
				dfs2(j + 1, s, path, res);
				path.remove(path.size() - 1);
			}
		}
	}

    private boolean isPalindrome(int l, int r, String s) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}
