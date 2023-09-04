package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #2769 https://leetcode.com/problems/find-the-maximum-achievable-number/description/
 */
public class FindTheMaximumAchievableNumber {

    // time O(1), space O(1)
    public int theMaximumAchievableX(int num, int t) {
        // since we need to get the maximum, we can infer
        // num + t = res - t -> res = num + 2*t
        return num + (t << 1);
    }
}
