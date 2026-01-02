package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/03/2020.
 * #1542 https://leetcode.com/problems/find-longest-awesome-substring/
 */
public class FindLongestAwesomeSubstring {

    // time O(n), space O(2^10)
    public int longestAwesomePrefixSumWithArray(String s) {
        // 因为 mask 的值最大为 1024， 所以可以用数组代替哈希表来优化。
        int res = 0, n = s.length();
        int[] map = new int[1_024];
        Arrays.fill(map, n);
        map[0] = -1;
        for (int i = 0, mask = 0; i < n; i++) {
            mask ^= 1 << (s.charAt(i) - '0');
            for (int j = 0; j < 10; j++) {
                int curr = mask ^ (1 << j);
                if (map[curr] != n) {
                    res = Math.max(res, i - map[curr]);
                }
            }
            if (map[mask] != n) {
                res = Math.max(res, i - map[mask]);
            } else {
                map[mask] = i;
            }
        }
        return res;
    }

    // time O(n), space O(2^10)
    public int longestAwesomePrefixSumWithHashMap(String s) {
        // 子字串可以形成回文的情况有两种:
        //   1. 长度为偶数，则字符必须两两成对 - 所有字符的数量为偶数
        //   2. 长度为奇数，则可以允许一个字符的数量为奇数其他字符的数量为偶数，
        //     多出来的这个数作为回文的中心。
        // 根据 #1177 同样的思路，用一个整形来存字符前缀数量的奇偶性的异或
        // 和。如果一个字符串可以形成回文，则两个这样的前缀和必须满足:
        //   1. 两前缀和相等 - 所有字符数量都是偶数
        //   2. 两前缀和右一个 bit 不一样 - 这个 bit 代表的字符的数量为奇数而
        //     其他字符的数量为偶数。
        int res = 0, n = s.length();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, mask = 0; i < n; i++) {
            mask ^= 1 << (s.charAt(i) - '0');
            for (int j = 0; j < 10; j++) {
                int curr = mask ^ (1 << j);
                if (map.containsKey(curr)) {
                    res = Math.max(res, i - map.get(curr));
                }
            }
            if (map.containsKey(mask)) {
                res = Math.max(res, i - map.get(mask));
            } else {
                map.put(mask, i);
            }
        }
        return res;
    }
}
