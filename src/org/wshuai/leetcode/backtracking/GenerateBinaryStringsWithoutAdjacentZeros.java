package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 07/04/2025.
 * #3211 https://leetcode.com/problems/generate-binary-strings-without-adjacent-zeros/
 */
public class GenerateBinaryStringsWithoutAdjacentZeros {

    // time O(n * 2^n), space O(n)
    public List<String> validStrings(int n) {
        List<String> res = new ArrayList<>();
        dfs(0, n, new StringBuilder(), res);
        return res;
    }

    private void dfs(int i, int n, StringBuilder sb, List<String> res) {
        if (i == n) {
            res.add(sb.toString());
            return;
        }
        int len = sb.length();
        // 在当前字符串后面加 1
        sb.append('1');
        dfs(i + 1, n, sb, res);
        sb.setLength(len);
        // 只有当字符串为空或者最后一个字符是 1 才可以在后面加 0
        if (sb.isEmpty() || sb.charAt(sb.length() - 1) == '1') {
            sb.append('0');
            dfs(i + 1, n, sb, res);
            sb.setLength(len);
        }
    }
}
