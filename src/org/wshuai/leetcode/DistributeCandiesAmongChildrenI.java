package org.wshuai.leetcode;

/**
 * Created by Wei on 12/17/2023.
 * #2928 https://leetcode.com/problems/distribute-candies-among-children-i/
 */
public class DistributeCandiesAmongChildrenI {

    // time O(n^2), space O(1)
    public int distributeCandies(int n, int limit) {
        int res = 0;
        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= limit; j++) {
                int k = n - i - j;
                if (k >= 0 && k <= limit) {
                    res++;
                }
            }
        }
        return res;
    }

    // time O(n^2), space O(n)
    public int distributeCandiesRecursive(int n, int limit) {
        return dfs(n, limit, 3);
    }

    private int dfs(int candies, int limit, int child) {
        if (child == 1) {
            return candies <= limit ? 1 : 0;
        }
        int res = 0;
        for (int i = 0; i <= Math.min(candies, limit); i++) {
            res += dfs(candies - i, limit, child - 1);
        }
        return res;
    }
}
