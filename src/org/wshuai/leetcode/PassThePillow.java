package org.wshuai.leetcode;

/**
 * Created by Wei on 08/01/2025.
 * #2582 https://leetcode.com/problems/pass-the-pillow/
 */
public class PassThePillow {

    // time O(1), space O(1)
    public int passThePillow(int n, int time) {
        // same as #3178 https://leetcode.com/problems/find-the-child-who-has-the-ball-after-k-seconds/
        int direction = time / (n - 1), position = time % (n - 1);
        return direction % 2 == 0 ? position + 1 : n - position;
    }
}
