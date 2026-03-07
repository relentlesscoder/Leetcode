package org.wshuai.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/08/2020.
 * #1371 https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
 */
public class FindTheLongestSubstringContainingVowelsInEvenCounts {

    private static final int[] MAPPING = new int[26];

    static {
        MAPPING['e' - 'a'] = 1;
        MAPPING['i' - 'a'] = 2;
        MAPPING['o' - 'a'] = 3;
        MAPPING['u' - 'a'] = 4;
    }

    // time O(n), space O(1)
    public int findTheLongestSubstringPrefixSumWithArray(String s) {
        // 在下解的基础上进一步优化空间。因为元音字母只有 5 个，所以我们可以用最低的 5
        // 个 bit 位来表示，这样可以把值域压缩到 2^5 (1 << 5)。用一个长度为 32 的数
        // 组来代替哈希表存前缀和。
        int res = 0, n = s.length();
        int[] prefix = new int[1 << 5];
        Arrays.fill(prefix, n);
        prefix[0] = -1;
        for (int i = 0, mask = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                mask ^= (1 << MAPPING[c - 'a']);
            }
            if (prefix[mask] != n) {
                res = Math.max(res, i - prefix[mask]);
            } else {
                prefix[mask] = i;
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int findTheLongestSubstringPrefixSumWithHashMap(String s) {
        // #1177 同样思路，维护一个哈希表存元音字母的数量的奇偶性的前缀异或和。如果
        // 两个索引具有相同的原因奇偶性，则它们直接形成的子数组中的原音字母数都是偶
        // 数。
        int res = 0, n = s.length();
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, -1);
        for (int i = 0, mask = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                mask ^= (1 << (c - 'a'));
            }
            if (prefix.containsKey(mask)) {
                res = Math.max(res, i - prefix.get(mask));
            } else {
                prefix.put(mask, i); // 我们需要最长子数组所以只记录最早出现的索引
            }
        }
        return res;
    }
}
