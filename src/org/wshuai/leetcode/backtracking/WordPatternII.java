package org.wshuai.leetcode.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 03/13/2017.
 * #0291 https://leetcode.com/problems/word-pattern-ii/
 */
public class WordPatternII {

	// time O(m * n^m), space O(n)
	public boolean wordPatternMatch(String pattern, String s) {
		String[] map = new String[26];
		return dfs(0, 0, pattern, s, map, new HashSet<>());
	}

	private boolean dfs(int i, int j, String pattern, String s, String[] map, Set<String> mapped) {
		if (i == pattern.length()) { // 已遍历完 pattern 的字符
			return j == s.length(); // 同时 s 也遍历完了则找到符合要求的规律
		}
		if (j == s.length()) { // 如果 s 提前遍历完则不符合要求
			return false;
		}
		int c = pattern.charAt(i) - 'a';
		if (map[c] == null) { // 如果当前字符还没有被使用
			for (int k = j + 1; k <= s.length(); k++) { // 尝试所有可能的字符串
				String str = s.substring(j, k);
				if (mapped.contains(str)) { // 字符串已经与别的字符配对了
					continue;
				}
				map[c] = str;
				mapped.add(str);
				if (dfs(i + 1, k, pattern, s, map, mapped)) { // 继续回溯
					return true;
				}
				// 回溯完恢复现场
				mapped.remove(str);
				map[c] = null;
			}
		} else if (j + map[c].length() <= s.length()
				&& map[c].equals(s.substring(j, j + map[c].length()))
				&& dfs(i + 1, j + map[c].length(), pattern, s, map, mapped)) {
			// 如果当前字符已经被使用且从当前索引开始的子串也匹配则继续回溯
			return true;
		}
		return false;
	}
}
