package org.wshuai.leetcode;

/**
 * Created by Wei on 02/10/2024.
 * #3028 https://leetcode.com/problems/ant-on-the-boundary/
 */
public class AntOnTheBoundary {

    // time O(n), space O(1)
    public int returnToBoundaryCount(int[] nums) {
        int res = 0, curr = 0;
        for (int num : nums) {
            curr += num;
            if (curr == 0) {
                res++;
            }
        }
        return res;
    }
}
