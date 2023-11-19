package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2023.
 * #2187 https://leetcode.com/problems/minimum-time-to-complete-trips/description/
 */
public class MinimumTimeToCompleteTrips {

    // time O(n*log(m*t)), space O(1)
    public long minimumTime(int[] time, int totalTrips) {
        long res = 0, left = 0, right = 0;
        int max = time[0];
        for (int i = 1; i < time.length; i++) {
            max = Math.max(time[i], max);
        }
        right = (long) max * totalTrips;
        while (left < right) {
            long mid = (left + right) / 2;
            if (canFinishTripes(time, mid, totalTrips)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean canFinishTripes(int[] time, long totalTime, long totalTrips) {
        long finished = 0;
        for (int t : time) {
            finished += totalTime / t;
        }
        return finished >= totalTrips;
    }
}
