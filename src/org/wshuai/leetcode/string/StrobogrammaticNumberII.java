package org.wshuai.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/24/2016.
 * #0247 https://leetcode.com/problems/strobogrammatic-number-ii/
 */
public class StrobogrammaticNumberII {

    private static final int[] MAPPING = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    // time O(n * 5^(n / 2)), space O(n / 2)
    public List<String> findStrobogrammatic(int n) {
        // 翻转 180 度相同的数有 [0, 0], [1, 1], [6, 9], [8, 8], [9, 6]。而且因为符合要求的数
        // 两边是根据这个关系对称的所以我们仅需要找到前半部分即可。如果 n 是奇数则中间还需要放一个与
        // 自己对称的数 - 0, 1, 或者 8.
        List<String> res = new ArrayList<>();
        int k = n / 2;
        dfs(0, k, new StringBuilder(), n, res);
        return res;
    }

    private void dfs(int i, int k, StringBuilder sb, int n, List<String> res) {
        if (i == k) { // 前半部分已经找到
            String s1 = sb.toString();
            // 根据 mapping 计算后半部分
            StringBuilder reverse = new StringBuilder();
            for (int j = 0; j < s1.length(); j++) {
                reverse.append(MAPPING[s1.charAt(j) - '0']);
            }
            String s2 = reverse.reverse().toString();
            if (n % 2 == 0) { // 偶数长度直接拼接两部分
                res.add(s1 + s2);
            } else { // 奇数长度还需要填入一个中间数字
                res.add(s1 + "0" + s2);
                res.add(s1 + "1" + s2);
                res.add(s1 + "8" + s2);
            }
            return;
        }
        for (int j = 0; j < 10; j++) {
            if (MAPPING[j] == -1) {
                continue;
            }
            if (j == 0 && sb.isEmpty()) { // 前导 0 不合要求
                continue;
            }
            int len = sb.length();
            sb.append(j);
            dfs(i + 1, k, sb, n, res);
            // 回溯之后恢复现场
            sb.setLength(len);
        }
    }
}
