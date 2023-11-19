package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/18/2023.
 * #2021 https://leetcode.com/problems/brightest-position-on-street/
 */
public class BrightestPositionOnStreet {

    // time O(n * log(n)), space O(n)
    public int brightestPosition(int[][] lights) {
        int n = lights.length;
        int[] start = new int[n], end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = lights[i][0] - lights[i][1];
            end[i] = lights[i][0] + lights[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int res = 0, max = 0, brightness = 0;
        for (int i = 0, j = 0; i < n && j < n;) {
            if (start[i] <= end[j]) {
                brightness++;
                if (brightness > max) {
                    max = brightness;
                    res = start[i];
                }
                i++;
            } else {
                brightness--;
                j++;
            }
        }
        return res;
    }
}
