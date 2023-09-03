package org.wshuai.leetcode;

/**
 * Created by Wei on 09/03/2023.
 * #2078 https://leetcode.com/problems/two-furthest-houses-with-different-colors/description/
 */
public class TwoFurthestHousesWithDifferentColors {

    // time O(n), space O(1)
    public int maxDistance(int[] colors) {
        int res = 0, left = 0, right = colors.length - 1;
        // the longest distance is the distance between first
        // pair two houses of different colors, we need to check from both side
        while (left < right && colors[left] == colors[right]) {
            left++;
        }
        res = right - left;
        left = 0;
        right = colors.length - 1;
        while (left < right && colors[left] == colors[right]) {
            right--;
        }
        return Math.max(res, right - left);
    }
}
