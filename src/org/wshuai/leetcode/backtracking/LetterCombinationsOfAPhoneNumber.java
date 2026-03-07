package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0017 https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber {

    private static final String[] MAPPING =
            new String[]{"#", "#", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    // time O(n * 4^n), space O(n)
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        List<String> res = new ArrayList<>();
        dfs(0, digits.toCharArray(), new char[n], res);
        return res;
    }

    private void dfs(int i, char[] s, char[] path, List<String> res) {
		// 结束条件
        if (i == s.length) {
            res.add(new String(path));
            return;
        }
        String chars = MAPPING[s[i] - '0'];
        for (int j = 0; j < chars.length(); j++) {
            char c = chars.charAt(j);
            path[i] = c; // 直接覆盖 i
            dfs(i + 1, s, path, res);
            // 注意因为每一次都会直接覆盖，所以回溯完毕无需打扫战场
        }
    }
}
