package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/30/2025.
 * #3714 https://leetcode.com/problems/longest-balanced-substring-ii/
 */
public class LongestBalancedSubstringII {

    private record Counter(int a, int b, int c) {}

    // time O(n), space O(n)
    public int longestBalanced(String s) {
        // 总共三种请情况:
        //   1. 子数组含有一种字符，比如 "aaa"。直接遍历数组计算最长的只含有
        //      一种字符的连续子数组。
        //   2. 子数组含有两种字符，比如 "aa"。分段遍历数组找到每段含有两种字
        //      符的子数组找到最长的两种字符数量相等的子数组 (#0525)。
        //   2. 子数组含有全部三种字符，比如 "abcabc"。则子数组种三种字符的数
        //      量 a, b, 和 c 与子数组的长度 l 需满足:
        //        a = l / 3, b = l / 3, c = l / 3
        //      假设子数组的开始索引为 i + 1， 结束索引为 j。
        //        a = (j - i) / 3, b = (j - i) / 3, c = (j - i) / 3
        //      将字符的数量用前缀和数组种字符数量差来表示:
        //        a1 - a0 = (j - i) / 3
        //        b1 - b0 = (j - i) / 3
        //        c1 - c0 = (j - i) / 3
        //      继续变形可得:
        //        3 * a1 - j = 3 * a0 - i
        //        3 * b1 - j = 3 * b0 - i
        //        3 * c1 - j = 3 * c0 - i
        //      根据上面的等式，可以用这三种字符的数量为哈希表的键而最早出现的索
        //      引为值来找到最长子数组。因为 n 的取值范围是 10^5 可以考虑把三个
        //      值压缩到一个长整型或者直接用 record 类型。
        char[] arr = s.toCharArray();
        int res = getLongestOne(arr), n = s.length();
        res = Math.max(res, getLongestTwo(arr, 'a', 'b'));
        res = Math.max(res, getLongestTwo(arr, 'a', 'c'));
        res = Math.max(res, getLongestTwo(arr, 'b', 'c'));
        Map<Counter, Integer> map = new HashMap<>();
        map.put(new Counter(1, 1, 1), -1);
        for (int i = 0, a = 0, b = 0, c = 0; i < n; i++) {
            int id = s.charAt(i) - 'a';
            a += (id == 0 ? 1 : 0);
            b += (id == 1 ? 1 : 0);
            c += (id == 2 ? 1 : 0);
            Counter cnt = new Counter(3 * a - i, 3 * b - i, 3 * c - i);
            if (map.containsKey(cnt)) {
                res = Math.max(res, i - map.get(cnt));
            } else {
                map.put(cnt, i);
            }
        }
        return res;
    }

    private int getLongestOne(char[] s) {
        int res = 0, n = s.length;
        for (int i = 0; i < n; ) {
            int start = i;
            for (i++; i < n && s[i] == s[i - 1]; i++);
            res = Math.max(res, i - start);
        }
        return res;
    }

    private int getLongestTwo(char[] s, char x, char y) {
        int res = 0, n = s.length;
        for (int i = 0; i < n; i++) {
            // 对每段子数组找到最长的两种字符数量相等的子数组 #0525
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, i - 1);
            for (int diff = 0; i < n && (s[i] == x || s[i] == y); i++) {
                diff += (s[i] == x ? 1 : -1);
                if (map.containsKey(diff)) {
                    res = Math.max(res, i - map.get(diff));
                }
                map.putIfAbsent(diff, i);
            }
        }
        return res;
    }
}
