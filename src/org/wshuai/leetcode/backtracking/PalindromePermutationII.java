package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2016.
 * #0267 https://leetcode.com/problems/palindrome-permutation-ii/
 */
public class PalindromePermutationII {

    // time O(n * (n/2)!), space O(n/2)
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        // 统计字符频率
        int[] freq = new int[26];
        // 用二进制掩码来判断 s 能否写成回文
        int mask = 0, n = s.length();
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
            // 相同的字符会两两抵消，最后只能最多有一个多余的字符 (奇数回文串的中点)
            mask ^= (1 << (c - 'a'));
        }
        // 如果最后无法配对的字符超过一个则直接返回
        if (Integer.bitCount(mask) > 1) {
            return res;
        }
        char[] cs = new char[n]; // 字符数组表示回文字符串
        if (n % 2 == 1) { // 如果长度为奇数，找到中心字符并直接放到数组中
            for (int i = 0; i < 26; i++) {
                if (freq[i] % 2 == 1) {
                    cs[n / 2] = (char) (i + 'a');
                    freq[i]--; // 记得从频率表中减去这个字符
                    break;
                }
            }
        }
        // 回溯找回文的前半部分 - 因为后半部分是一样的
        dfs(0, n / 2, cs, freq, res);
        return res;
    }

    private void dfs(int i, int x, char[] cs, int[] freq, List<String> res) {
        int n = cs.length;
        if (i == x) {
            res.add(new String(cs));
            return;
        }
        for (int j = 0; j < 26; j++) {
            if (freq[j] == 0) {
                continue;
            }
            freq[j] -= 2; // 每次用掉两个字符用于回文串对称的前后两个位置
            cs[i] = (char) (j + 'a'); // 前
            cs[n - 1 - i] = (char) (j + 'a'); // 后
            dfs(i + 1, x, cs, freq, res);
            freq[j] += 2; // 回溯完恢复现场
        }
    }
}
