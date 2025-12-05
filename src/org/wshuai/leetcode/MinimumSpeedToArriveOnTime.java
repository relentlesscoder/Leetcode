package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2024.
 * #1870 https://leetcode.com/problems/minimum-speed-to-arrive-on-time/
 */
public class MinimumSpeedToArriveOnTime {

    // time O(n * log(n)), space O(1)
    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length > (int)Math.ceil(hour)) {
            return -1;
        }
        int low = 1, high = (int)1e7; // the maximum speed is 10,000,000 since the possible max distance is 100,000 and min hour is 0.01
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canArrive(dist, mid, hour)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canArrive(int[] dist, int threshold, double hour) {
        double time = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            time += (dist[i] + threshold - 1) / threshold;
            if (hour < time) {
                return false;
            }
        }
        time += 1.0 * dist[dist.length - 1] / threshold; // only for the last stop, we can use the fraction hour
        return hour >= time;
    }
}
