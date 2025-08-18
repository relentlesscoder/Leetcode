package org.wshuai.leetcode;

/**
 * Created by Wei on 08/17/2025.
 * #3439 https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/
 */
public class RescheduleMeetingsForMaximumFreeTimeI {

    // time O(n), space O(1)
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        // try to merge each adjacent k events, the continuous period of free time for
        // k events start at i - k + 1 and end at i is
        // right - left - sum(endTime[e] - startTime[e])
        int res = 0, n = startTime.length;
        for (int i = 0, t = 0; i < n; i++) {
            t += endTime[i] - startTime[i];
            int left = i <= k - 1 ? 0 : endTime[i - k];
            int right = i == n - 1 ? eventTime : startTime[i + 1];
            res = Math.max(res, right - left - t);
            if (i >= k - 1) {
                t -= endTime[i - k + 1] - startTime[i - k + 1];
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int maxFreeTimePrefixSum(int eventTime, int k, int[] startTime, int[] endTime) {
        int res = 0, n = startTime.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + endTime[i - 1] - startTime[i - 1];
        }
        for (int i = k - 1; i < n; i++) {
            int right = i == n - 1 ? eventTime : startTime[i + 1];
            int left = i == k - 1 ? 0 : endTime[i - k];
            res = Math.max(res, right - left - (prefixSum[i + 1] - prefixSum[i - k + 1]));
        }
        return res;
    }
}
