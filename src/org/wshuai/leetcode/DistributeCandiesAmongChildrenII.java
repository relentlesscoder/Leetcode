package org.wshuai.leetcode;

/**
 * Created by Wei on 08/03/2025.
 * #2929 https://leetcode.com/problems/distribute-candies-among-children-ii/
 */
public class DistributeCandiesAmongChildrenII {

    // time O(1), space O(1)
    public long distributeCandiesCombinatorics(int n, int limit) {
        // https://leetcode.com/problems/distribute-candies-among-children-ii/solutions/4276948/o-1-time-o-1-space-beats-100-detailed-explanation/?envType=company&envId=facebook&favoriteSlug=facebook-all
        return (
                calculate(n + 2) -
                        3 * calculate(n - limit + 1) +
                        3 * calculate(n - (limit + 1) * 2 + 2) -
                        calculate(n - 3 * (limit + 1) + 2)
        );
    }

    private long calculate(int x) {
        if (x < 0) {
            return 0;
        }
        return ((long) x * (x - 1)) / 2;
    }

    // time O(n), space O(1)
    public long distributeCandies(int n, int limit) {
        long res = 0;
        for (int i = 0; i <= Math.min(limit, n); i++) {
            if (n - i > 2 * limit) {
                continue;
            }
            res += Math.min(n - i, limit) - Math.max(0, n - i - limit) + 1;
        }
        return res;
    }
}
