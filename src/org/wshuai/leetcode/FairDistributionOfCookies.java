package org.wshuai.leetcode;

/**
 * Created by Wei on 09/08/2023.
 * #2305 https://leetcode.com/problems/fair-distribution-of-cookies/
 */
public class FairDistributionOfCookies {

    private int min = Integer.MAX_VALUE;

    // time O(k^n), space O(k + n)
    public int distributeCookies(int[] cookies, int k) {
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
