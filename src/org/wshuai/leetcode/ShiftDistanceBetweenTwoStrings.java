package org.wshuai.leetcode;

/**
 * Created by Wei on 12/26/2025.
 * #3361 https://leetcode.com/problems/shift-distance-between-two-strings/
 */
public class ShiftDistanceBetweenTwoStrings {

    // time O(n), space O(1)
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        long res = 0;
        int n = s.length();
        long[] prefix1 = new long[27], prefix2 = new long[27];
        // prefix1为后移代价数组nextCost的前缀和，prefix2为前移代价数组previousCost的前缀和。
        for (int i = 0; i < 26; i++) {
            prefix1[i + 1] = prefix1[i] + nextCost[i];
            prefix2[i + 1] = prefix2[i] + previousCost[i];
        }
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (c1 == c2) {
                continue;
            }
            int idx1 = c1 - 'a', idx2 = c2 - 'a';
            // 两种情况：
            //   1. c1 < c2: c1后移到c2或者c1前移到a然后z前移到c2两者代价的最小值
            //   2. c1 > c2: c1前移到c2或者c1后移到z然后a后移到c2两者代价的最小值
            // 注意目标字母本身的代价是不算的。比如从'a'移动到'c', 只计算'a'和'b'的代价。
            if (c1 < c2) {
                long cost1 = prefix1[idx2] - prefix1[idx1]; // c1后移到c2
                long cost2 = prefix2[idx1 + 1] + prefix2[26] - prefix2[idx2 + 1]; // c1前移到a然后z前移到c2
                res += Math.min(cost1, cost2);
            } else {
                long cost1 = prefix2[idx1 + 1] - prefix2[idx2 + 1]; // c1前移到c2
                long cost2 = prefix1[idx2] + prefix1[26] - prefix1[idx1]; // c1后移到z然后a后移到c2
                res += Math.min(cost1, cost2);
            }
        }
        return res;
    }
}
