package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/01/2025.
 * #3186 https://leetcode.com/problems/maximum-total-damage-with-spell-casting/
 */
public class MaximumTotalDamageWithSpellCasting {

    // time O(n * log(n)), space O(n)
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> powerMap = new HashMap<>();
        for (int p : power) {
            powerMap.put(p, powerMap.getOrDefault(p, 0) + 1);
        }
        int n = powerMap.size(), k = 0;
        int[] arr = new int[n];
        for (int key : powerMap.keySet()) {
            arr[k++] = key;
        }
        Arrays.sort(arr);

        long[] dp = new long[n + 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            int p = arr[i];
            while (arr[j] < p - 2) {
                j++;
            }
            dp[i + 1] = Math.max(dp[i], dp[j] + (long) p * powerMap.get(p));
        }
        return dp[n];
    }

    // time O(n * log(n)), space O(n)
    public long maximumTotalDamageMemorization(int[] power) {
        Map<Integer, Integer> powerMap = new HashMap<>();
        for (int p : power) {
            powerMap.put(p, powerMap.getOrDefault(p, 0) + 1);
        }
        int n = powerMap.size(), i = 0;
        int[] arr = new int[n];
        for (int key : powerMap.keySet()) {
            arr[i++] = key;
        }
        Arrays.sort(arr);
        long[] memo = new long[n];
        Arrays.fill(memo, -1);
        return dfs(arr, powerMap, memo, n - 1);
    }

    private long dfs(int[] arr, Map<Integer, Integer> powerMap, long[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int power = arr[i], j = i;
        while (j > 0 && arr[j - 1] >= power - 2) {
            j--;
        }
        return memo[i] = Math.max(dfs(arr, powerMap, memo, i - 1),
                dfs(arr, powerMap, memo, j - 1) + (long) power * powerMap.get(power));
    }
}
