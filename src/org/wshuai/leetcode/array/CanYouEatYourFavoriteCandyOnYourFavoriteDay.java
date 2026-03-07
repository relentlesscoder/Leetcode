package org.wshuai.leetcode.array;

/**
 * Created by Wei on 01/02/2026.
 * #1744 https://leetcode.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/
 */
public class CanYouEatYourFavoriteCandyOnYourFavoriteDay {

    // time O(n + m), space O(n + m)
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length, m = queries.length;
        boolean[] res = new boolean[m];
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + candiesCount[i];
        }
        for (int i = 0; i < m; i++) {
            int t = queries[i][0], d = queries[i][1], dc = queries[i][2];
            // 两种情况下是不可能吃到的:
            //   1. 在前 d + 1 天 ([0, d]) 每天吃满配额 dc ，还是没办法把 t 类型之前的糖果全部
            //      吃完。注意只要能在第 d 天以及以前能把 t 之前的糖果吃完，哪怕在第 d 天只吃一颗
            //      t 类型的糖也是合法的，因为题目允许一天吃多种类型的糖。
            //   2. 在前 d + 1 天之前 ([0, d - 1]) 每天只吃 1 颗糖，还是能把包括 t 类型以及它
            //      之前的所有糖果都吃完。
            if ((long) dc * (d + 1) <= prefix[t] || d + 1 > prefix[t + 1]) {
                continue;
            }
            res[i] = true;
        }
        return res;
    }
}
