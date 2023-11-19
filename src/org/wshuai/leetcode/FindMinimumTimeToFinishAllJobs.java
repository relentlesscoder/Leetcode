package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/09/2023.
 * #1723 https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/
 */
public class FindMinimumTimeToFinishAllJobs {

    // #2305 https://leetcode.com/problems/fair-distribution-of-cookies/

    // time O(k^n * log(r - l)), space O(k * log(r - l))
    public int minimumTimeRequiredBinarySearch(int[] jobs, int k) {
        int left = jobs[0], right = jobs[0];
        for (int i = 1; i < jobs.length; i++) {
            left = Math.max(left, jobs[i]);
            right += jobs[i];
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinishJobs(jobs, new int[k], 0, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canFinishJobs(int[] jobs, int[] worker, int curr, int threshold) {
        if (curr == jobs.length) {
            return true;
        }
        for (int j = 0; j < worker.length; j++) {
            if (worker[j] + jobs[curr] <= threshold) {
                worker[j] += jobs[curr];
                if (canFinishJobs(jobs, worker, curr + 1, threshold)) {
                    return true;
                }
                worker[j] -= jobs[curr];
            }
            if (worker[j] == 0) {
                break;
            }
        }
        return false;
    }

    // time O(3^n), space O(k * (2^n))
    public int minimumTimeRequiredDP(int[] jobs, int k) {
        int n = jobs.length, m = 1 << n;
        int[] sum = new int[m];
        int[][] dp = new int[k + 1][m];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int mask = 0; mask < m; mask++) {
            int total = 0;
            for (int i = 0; i < jobs.length; i++) {
                if ((mask & (1 << i)) > 0) {
                    total += jobs[i];
                }
            }
            sum[mask] = total;
        }
        dp[0][0] = 0;
        for (int person = 1; person <= k; person++) {
            for (int mask = 0; mask < m; mask++) {
                for (int submask = mask; submask > 0; submask = (submask -1) & mask) {
                    dp[person][mask] = Math.min(dp[person][mask], Math.max(sum[submask], dp[person - 1][submask ^ mask]));
                }
            }
        }
        return dp[k][m - 1];
    }
}
