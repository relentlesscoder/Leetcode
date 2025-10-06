package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/07/2025.
 * #3027 https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii/
 */
public class FindTheNumberOfWaysToPlacePeopleII {

    // time O(n^2), space O(1)
    public int numberOfPairs(int[][] points) {
        // https://leetcode.cn/problems/find-the-number-of-ways-to-place-people-ii/solutions/2630655/on2-you-ya-mei-ju-by-endlesscheng-z86d/
        int res = 0;
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int i = 0; i < points.length; i++) {
            int y1 = points[i][1], maxY = Integer.MIN_VALUE;
            for (int j = i + 1; j < points.length && maxY < y1; j++) {
                int y2 = points[j][1];
                if (y2 <= y1 && y2 > maxY) {
                    maxY = y2;
                    res++;
                }
            }
        }
        return res;
    }
}
