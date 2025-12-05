package org.wshuai.leetcode;

/**
 * Created by Wei on 08/01/2025.
 * #3178 https://leetcode.com/problems/find-the-child-who-has-the-ball-after-k-seconds/
 */
public class FindTheChildWhoHasTheBallAfterKSeconds {

    // time O(1), space O(1)
    public int numberOfChild(int n, int k) {
        // same as #2582 https://leetcode.com/problems/pass-the-pillow/
        int direction = k / (n - 1), position = k % (n - 1);
        return direction % 2 == 0 ? position : n - 1 - position;
    }
}
