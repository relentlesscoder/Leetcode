package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/19/2023.
 * #2037 https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/
 */
public class MinimumNumberOfMovesToSeatEveryone {

    // time O(n * log(n)), space O(log(n))
    public int minMovesToSeat(int[] seats, int[] students) {
        int res = 0;
        Arrays.sort(seats);
        Arrays.sort(students);
        for (int i = 0; i < seats.length; i++) {
            res += Math.abs(seats[i] - students[i]);
        }
        return res;
    }
}
