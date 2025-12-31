package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/30/2025.
 * #2489 https://leetcode.cn/problems/number-of-substrings-with-fixed-ratio/
 */
public class NumberOfSubstringsWithFixedRatio {

    // time O(n), space O(n)
    public long fixedRatio(String s, int num1, int num2) {
        // 根据题意满足要求的子数组中 0 的数量和 1 的数量满足:
        //   0s / 1s = n1 / n2
        // 将数量用前缀和之差表示:
        //   (j0 - i0) * n2 = (j1 - i1) * n1
        //   j0 * n2 - i0 * n2 = j1 * n1 - i1 * n1
        // 移项后得到公式:
        //   j0 * n2 - j1 * n1 = i0 * n2 - i1 * n1
        long res = 0, zero = 0, one = 0;
        int n = s.length();
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0L, 1);
        for (int i = 0; i < n; i++) {
            int v = s.charAt(i) - '0';
            zero += 1 - v;
            one += v;
            long key = zero * num2 - one * num1;
            res += cnt.getOrDefault(key, 0);
            cnt.merge(key, 1, Integer::sum);
        }
        return res;
    }
}
