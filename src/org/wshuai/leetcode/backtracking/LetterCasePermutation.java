package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/19/2019.
 * #0784 https://leetcode.com/problems/letter-case-permutation/
 */
public class LetterCasePermutation {

    // time O(2^n), space O(n)
    public List<String> letterCasePermutation(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();
        dfs(0, s.toCharArray(), new char[n], res);
        return res;
    }

    private void dfs(int i, char[] s, char[] path, List<String> res) {
        if (i == s.length) {
            res.add(new String(path));
            return;
        }
        path[i] = s[i];
        dfs(i + 1, s, path, res); // 不改变当前字符
        if (Character.isLetter(s[i])) { // 如果当前字符是字母，需要转换大小写 (树多一个分支)
            path[i] = s[i] >= 'a' && s[i] <= 'z' ?
                    (char) ('A' + s[i] - 'a') : (char) ('a' + s[i] - 'A');
            dfs(i + 1, s, path, res);
        }
    }
}
