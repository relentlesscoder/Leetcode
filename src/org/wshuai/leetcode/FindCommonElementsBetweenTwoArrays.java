package org.wshuai.leetcode;

/**
 * Created by Wei on 12/23/2023.
 * #2956 https://leetcode.com/problems/find-common-elements-between-two-arrays/
 */
public class FindCommonElementsBetweenTwoArrays {

    // time O(m + n), space O(1)
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] res = new int[2], map1 = new int[101], map2 = new int[101];
        for (int n : nums1) {
            map1[n]++;
        }
        for (int n : nums2) {
            map2[n]++;
        }
        for (int i = 1; i <= 100; i++) {
            res[0] += map2[i] > 0 ? map1[i] : 0;
            res[1] += map1[i] > 0 ? map2[i] : 0;
        }
        return res;
    }
}
