package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/05/2020.
 * #0022 https://leetcode.com/problems/generate-parentheses/
 */
public class GenerateParentheses {

    // time O(n * C(2n, n)), space O(n)
    public List<String> generateParenthesis(int n) {
        // 需要在 0, 1, 2, … 2n−1 中选 n 个数（位置）填入左括号，其余 n 位置填入右括号。
        List<String> res = new ArrayList<>();
        dfs(0, 0, n, new char[n * 2], res);
        return res;
    }

    private void dfs(int left, int right, int n, char[] path, List<String> res) {
        if (right == n) {
            res.add(new String(path));
            return;
        }
        if (left < n) { // left 没到 n 还可以继续选
            path[left + right] = '(';
            dfs(left + 1, right, n, path, res);
        }
        if (right < left) { // right 小于 left 可以选 right，如果 right >= left 则不能选 right
            path[left + right] = ')';
            dfs(left, right + 1, n, path, res);
        }
    }
}
