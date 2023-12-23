package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/23/2023.
 * #2323 https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs-ii/
 */
public class FindMinimumTimeToFinishAllJobsII {

    // time O(n * log(n)), space O(1)
    public int minimumTime(int[] jobs, int[] workers) {
        int res = 0;
        Arrays.sort(jobs);
        Arrays.sort(workers);
        // greedily assign the worker who can work the largest number of hours per day to the work that requires the largest number of hours to complete
        for (int i = 0; i < jobs.length; i++) {
            res = Math.max(res, (jobs[i] + workers[i] - 1) / workers[i]);
        }
        return res;
    }
}
