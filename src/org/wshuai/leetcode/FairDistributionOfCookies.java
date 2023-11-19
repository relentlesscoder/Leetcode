package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/08/2023.
 * #2305 https://leetcode.com/problems/fair-distribution-of-cookies/
 */
public class FairDistributionOfCookies {

    // #1723 https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/

    // time O(k^n * log(r - l)), space O(k * log(r - l))
    public int distributeCookiesBinarySearch(int[] cookies, int k) {
        int left = cookies[0], right = cookies[0];
        for (int i = 1; i < cookies.length; i++) {
            left = Math.max(left, cookies[i]);
            right += cookies[i];
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canDistributeCandies(cookies, new int[k], 0, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canDistributeCandies(int[] cookies, int[] children, int curr, int threshold) {
        if (curr == cookies.length) {
            return true;
        }
        for (int j = 0; j < children.length; j++) {
            if (children[j] + cookies[curr] <= threshold) {
                children[j] += cookies[curr];
                if (canDistributeCandies(cookies, children, curr + 1, threshold)) {
                    return true;
                }
                children[j] -= cookies[curr];
            }
            if (children[j] == 0) {
                break;
            }
        }
        return false;
    }

    // time O(3^n), space O(k * (2)^n)
    public int distributeCookiesDP(int[] cookies, int k) {
        int n = cookies.length, m = 1 << n;
        int[][] dp = new int[k + 1][m];
        int[] sum = new int[m];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // sum denotes the fairness value for all configurations to distribute n candies to one person
        // for n = 3, we have configuration "000", "001", "010", "011", "100", "101", "110", "111"
        for (int mask = 0; mask < m; mask++) {
            int total = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) > 0) {
                    total += cookies[i];
                }
            }
            sum[mask] = total;
        }
        // dp[p][m] denotes the optimal when distribute candies first p person by configuration m
        dp[0][0] = 0;
        for (int person = 1; person <= k; person++) {
            // https://cp-algorithms.com/algebra/all-submasks.html#enumerating-all-submasks-of-a-given-mask
            for (int mask = 0; mask < m; mask++) {
                for (int submask = mask; submask > 0; submask = (submask - 1) & mask) {
                    dp[person][mask] = Math.min(dp[person][mask], Math.max(sum[submask], dp[person - 1][mask ^ submask]));
                }
            }
        }
        return dp[k][m - 1];
    }

    private int min = Integer.MAX_VALUE;

    // time O(k^n), space O(k + n)
    public int distributeCookiesBacktracking(int[] cookies, int k) {
        int[] dist = new int[k];
        dfs(cookies, 0, dist);
        return min;
    }

    private void dfs(int[] cookies, int curr, int[] dist) {
        if (curr == cookies.length) {
            int unfairness = dist[0];
            for (int i = 1; i < dist.length; i++) {
                unfairness = Math.max(unfairness, dist[i]);
            }
            min = Math.min(min, unfairness);
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            dist[i] += cookies[curr];
            dfs(cookies, curr + 1, dist);
            dist[i] -= cookies[curr];
        }
    }
}
