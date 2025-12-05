package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/10/2024.
 * #2141 https://leetcode.com/problems/maximum-running-time-of-n-computers/
 */
public class MaximumRunningTimeOfNComputers {

    // time O(m * log(k)), space O(1)
    public long maxRunTimeBinarySearch(int n, int[] batteries) {
        long low = 1, high = 0;
        for (int b : batteries) {
            high += b;
        }
        high /= n;
        while (low < high) {
            long mid = low + (high - low + 1) / 2, extra = 0;
            for (int b : batteries) {
                extra += Math.min(b, mid);
            }
            if (extra >= mid * n) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // time O(m * log(m)), space O(log(m))
    public long maxRunTime(int n, int[] batteries) {
        // See https://leetcode.com/problems/maximum-running-time-of-n-computers/editorial/
        Arrays.sort(batteries);
        long extra = 0;
        for (int i = 0; i < batteries.length - n; i++) {
            extra += batteries[i];
        }
        int[] live = new int[n];
        System.arraycopy(batteries, batteries.length - n, live, 0, n);
        for (int i = 0; i < n - 1; i++) {
            if (extra < (long)(i + 1) * (live[i + 1] - live[i])) {
                return live[i] + extra / (long)(i + 1);
            }
            extra -= (long)(i + 1) * (live[i + 1] - live[i]);
        }
        return live[n - 1] + extra / n;
    }
}
