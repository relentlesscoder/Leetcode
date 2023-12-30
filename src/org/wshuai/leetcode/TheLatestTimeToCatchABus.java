package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/29/2023.
 * #2332 https://leetcode.com/problems/the-latest-time-to-catch-a-bus/
 */
public class TheLatestTimeToCatchABus {

    // time O(n * log(n) + m * log(m))
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        int n = buses.length, m = passengers.length;
        Arrays.sort(buses);
        Arrays.sort(passengers);
        for (int i = 0, j = 0; i < n; i++) {
            int curr = 0;
            while (curr < capacity && j < m && passengers[j] <= buses[i]) {
                curr++;
                j++;
            }
            if (i == n - 1) {
                j--; // reset the pointer to the last onboarded passenger
                if (curr < capacity) { // if bus is not full, you can board at bus arrival time (if available) or any earlier time that is available
                    return findTime(passengers, buses[i], j);
                } else { // if bus is not full, you must board before the last person boarded on the bus
                    return findTime(passengers, passengers[j] - 1, j - 1);
                }
            }
        }
        return -1;
    }

    private int findTime(int[] passengers, int time, int start) {
        while (start >= 0 && passengers[start] == time) {
            time--;
            start--;
        }
        return time;
    }
}
