package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/02/2023.
 * #2222 https://leetcode.com/problems/number-of-ways-to-select-buildings/
 */
public class NumberOfWaysToSelectBuildings {

    // time O(n), space O(1)
    public long numberOfWays(String s) {
        long res = 0, zeros = 0, ones = 0, leftZeros = 0, leftOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                zeros++;
            }
        }
        ones = s.length() - zeros;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                res += leftZeros * (zeros - leftZeros);
                leftOnes++;
            } else {
                res += leftOnes * (ones - leftOnes);
                leftZeros++;
            }
        }
        return res;
    }

    // time O(n), space O(1)
    public long numberOfWaysDP(String s) {
        Map<String, Long> dp = new HashMap<>() {{
            put("0", 0L);
            put("1", 0L);
            put("01", 0L);
            put("10", 0L);
            put("010", 0L);
            put("101", 0L);
        }};
        for (char c : s.toCharArray()) {
            if (c == '0') {
                dp.put("0", dp.getOrDefault("0", 0L) + 1L);
                dp.put("10", dp.getOrDefault("10", 0L) + dp.getOrDefault("1", 0L));
                dp.put("010", dp.getOrDefault("010", 0L) + dp.getOrDefault("01", 0L));
            } else {
                dp.put("1", dp.getOrDefault("1", 0L) + 1L);
                dp.put("01", dp.getOrDefault("01", 0L) + dp.getOrDefault("0", 0L));
                dp.put("101", dp.getOrDefault("101", 0L) + dp.getOrDefault("10", 0L));
            }
        }
        return dp.getOrDefault("010", 0L) + dp.getOrDefault("101", 0L);
    }

    private long[][][] memo;

    // time O(n^2), space O(n)
    public long numberOfWaysDFS(String s) {
        memo = new long[s.length()][3][4];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(s, 0, 2, 0);
    }

    private long dfs(String s, int index, int prev, int len) {
        if (len == 3) {
            return 1;
        }
        if (index == s.length()) {
            return 0;
        }
        if (memo[index][prev][len] != -1) {
            return memo[index][prev][len];
        }
        memo[index][prev][len] = dfs(s, index + 1, prev, len);
        if (prev != (s.charAt(index) - '0')) {
            memo[index][prev][len] += dfs(s, index + 1, s.charAt(index) - '0', len + 1);
        }
        return memo[index][prev][len];
    }
}
