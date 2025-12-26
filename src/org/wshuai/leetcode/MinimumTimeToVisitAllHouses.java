package org.wshuai.leetcode;

/**
 * Created by Wei on 12/26/2025.
 * #3540 https://leetcode.com/problems/minimum-time-to-visit-all-houses/
 */
public class MinimumTimeToVisitAllHouses {

    // time O(n), space O(n)
    public long minTotalTime(int[] forward, int[] backward, int[] queries) {
        // #3361相似题
        long res = 0;
        int n = forward.length, m = queries.length,
                forwardCost = forward[n - 1], // 从n - 1到0的代价
                backwardCost = backward[0]; // 从0到n - 1的代价
        long[] pf = new long[n], pb = new long[n];
        // 数组pf是前进代价数组forward的前缀和
        for (int i = 1; i < n; i++) {
            pf[i] = pf[i - 1] + forward[i - 1];
        }
        // 数组bf是后退代价数组backward的前缀和
        for (int i = 1; i < n; i++) {
            pb[i] = pb[i - 1] + backward[i];
        }
        for (int i = 0, pre = 0; i < m; i++) {
            int id = queries[i];
            if (id == pre) {
                continue;
            }
            // 两种情况：
            //   1. id > pre: 从房子pre前进到id或者从pre后退到房子0然后n - 1再后退到id两者代价的最小值
            //   2. id < pre: 从房子pre后退到id或者从pre前进到房子n - 1然后0再前进到id两者代价的最小值
            if (id > pre) {
                long cost1 = pf[id] - pf[pre]; // 从房子pre前进到id
                long cost2 = backwardCost + pb[pre] + pb[n - 1] - pb[id]; // 从pre后退到房子0然后n - 1再后退到id
                res += Math.min(cost1, cost2);
            } else {
                long cost1 = pb[pre] - pb[id]; // 从房子pre后退到id
                long cost2 = forwardCost + pf[n - 1] - pf[pre] + pf[id]; // 从pre前进到房子n - 1然后0再前进到id
                res += Math.min(cost1, cost2);
            }
            pre = id;
        }
        return res;
    }
}
