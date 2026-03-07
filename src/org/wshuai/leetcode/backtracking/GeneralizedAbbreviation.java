package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2016.
 * #0320 https://leetcode.com/problems/generalized-abbreviation/
 */
public class GeneralizedAbbreviation {

    // time O(m * 2^n), space O(n)
    public List<String> generateAbbreviationsBacktracking(String word) {
        List<String> res = new ArrayList<>();
        dfs(0, new StringBuilder(), 0, word.toCharArray(), res);
        return res;
    }

    private void dfs(int i, StringBuilder sb, int count, char[] word, List<String> res) {
        if (i == word.length) {
            res.add(sb.toString() + (count > 0 ? Integer.toString(count) : ""));
            return;
        }
        // 缩写当前字符 - 把当前的字符加入到当前缩写字符的长度中
        dfs(i + 1, sb, count + 1, word, res);
        // 不缩写当前字符 - 注意如果选择不缩写，则先把当前缩写的字符的长度先写到结果中。
        // 这样可以保证不会有两个相邻的缩写。
        int len = sb.length();
        if (count > 0) {
            sb.append(count);
        }
        sb.append(word[i]);
        dfs(i + 1, sb, 0, word, res);
        sb.setLength(len); // 回溯完复原战场
    }

    // time O(n * 2^n), space O(1)
    public List<String> generateAbbreviationsBitMask(String word) {
        List<String> res = new ArrayList<>();
        int n = word.length(), m = 1 << n;
        // 二进制掩码中 0 表示不缩写 1 表示缩写。
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) > 0) {
                    count++;
                } else {
                    if (count > 0) {
                        sb.append(count);
                    }
                    sb.append(word.charAt(j));
                    count = 0;
                }
            }
            if (count > 0) {
                sb.append(count);
            }
            res.add(sb.toString());
        }
        return res;
    }
}
