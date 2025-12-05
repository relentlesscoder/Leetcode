package org.wshuai.leetcode;

/**
 * Created by Wei on 08/17/2025.
 * #3439 https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/
 */
public class RescheduleMeetingsForMaximumFreeTimeI {

    // time O(n), space O(1)
    public int maxFreeTimeSlidingWindow(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length, res = 0,
                duration = 0, // Total duration of k consecutive meetings
                free = 0, // Continuous free time at the end of current window
                start = 0; // Start time of the current window
        for (int i = 0; i < n; i++) {
            duration += endTime[i] - startTime[i];
            if (i - k + 1 < 0) {
                continue;
            }
            // Reschedule all consecutive k meetings to the end of the meeting before
            // the current window (at endTime[i - k] or 0 if this is the first window).
            // This will maximize the continuous free time between the end of current
            // window and the start of next window (startTime[i + 1] or eventTime if
            // this is the last window).
            free = (i == n - 1 ? eventTime : startTime[i + 1]) - start - duration;
            res = Math.max(res, free);
            // Move the meeting out of the sliding window
            duration -= endTime[i - k + 1] - startTime[i - k + 1];
            // Maintain the start time
            start = endTime[i - k + 1];
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
